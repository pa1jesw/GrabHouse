package com.fcproject.grabhouce;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.google.android.gms.location.LocationServices;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.erikagtierrez.multiple_media_picker.Gallery;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */


/*-------------------------------Note----------------------------------*/
//For Multiple Image View Implement the following Library
//https://github.com/stfalcon-studio/FrescoImageViewer
//Multi Image Selector code is working but commented
//there has to be multiple images for one property that's what we need

public class PageThree extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    Button btnChoose, btnUpload;
    EditText etTitle, etPrice, etLocation, etNumber;
    ImageView ivImg;
    static final int PICK_IMAGE_REQUEST = 123;
    private Uri filePath;
    static final int OPEN_MEDIA_PICKER = 1;  // Request code for api multiImager
    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    String selection = "Sell";
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private GoogleApiClient mLocationClient;
    private Location mLastLoc;
    MaterialSpinner materialSpinner;
    private ArrayList<String> selectionResult;

    @Override
    public void onStart() {
        super.onStart();
        if (mLocationClient != null) {
            mLocationClient.connect();
        }

    }

    public static PageThree newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        PageThree pageThree = new PageThree();
        pageThree.setArguments(args);
        return pageThree;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_three, container, false);
        btnChoose = view.findViewById(R.id.btnChoose);
        btnUpload = view.findViewById(R.id.btnUpload);
        etTitle = view.findViewById(R.id.etTitle);
        etPrice = view.findViewById(R.id.etPrice);
        etLocation = view.findViewById(R.id.etLocation);
        etNumber = view.findViewById(R.id.etNumber);
        ivImg = view.findViewById(R.id.ivImg);
        //spnspin=view.findViewById(R.id.rentorpurchase);
        materialSpinner = view.findViewById(R.id.rentorpurchase);
        materialSpinner.setItems("Sell", "Lease");
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selection = (String) item;
            }


        });
//      final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.rentorpurchase, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnspin.setAdapter(adapter);
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                .addApi(LocationServices.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        mLocationClient = builder.build();
        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

//       spnspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//           @Override
//           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//               selection= (String) adapterView.getItemAtPosition(i);
//           }
//
//           @Override
//           public void onNothingSelected(AdapterView<?> adapterView) {
//
//           }
//       });
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Multi Image Selector Working
                /*Intent intent = new Intent(getActivity(), Gallery.class);
                intent.putExtra("title", "Select media");
                // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
                intent.putExtra("mode", 2);
                intent.putExtra("maxSelection", 5); // Optional
                startActivityForResult(intent, OPEN_MEDIA_PICKER);*/
                showFileChooser();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });


        return view;
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                //Toast.makeText(getActivity().getApplicationContext(), ""+filePath, Toast.LENGTH_SHORT).show();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                ivImg.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == OPEN_MEDIA_PICKER) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {
                //ArrayList of the path
                selectionResult = data.getStringArrayListExtra("result");
                //Setting first image on main page
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),
                            Uri.parse(selectionResult.get(0).toString()));
                    ivImg.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(getActivity().getApplicationContext(), ""+Uri.parse(selectionResult.get(0).toString()), Toast.LENGTH_SHORT).show();
                btnChoose.setText("Upload Property->");
                btnChoose.setEnabled(false);
            }
        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadFile() {
        //checking if file list is not empty
       // if (selectionResult.size() != 0) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            StorageReference sRef =
                    storageReference.child(Constants.STORAGE_PATH_UPLOADS +
                            System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "File Uploaded ", Toast.LENGTH_LONG).show();
                            String title = etTitle.getText().toString().trim();
                            String number = etNumber.getText().toString();
                            String location = etLocation.getText().toString();
                            String price = etPrice.getText().toString();

                            //creating the upload object to store uploaded image details
                            Upload upload = new
                                    Upload( title, price, location, number, selection,
                                    taskSnapshot.getDownloadUrl().toString());

                            //adding an upload to firebase database
                            String uploadId = mDatabase.push().getKey();
                            mDatabase.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        /*} else {

            //display an error if no file is selected
            Toast.makeText(getActivity().getApplicationContext(), "Error while uploading", Toast.LENGTH_LONG).show();
        }*/

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLoc = LocationServices.FusedLocationApi.getLastLocation(mLocationClient);
        if(mLastLoc!=null)
        {
            double lat=mLastLoc.getLatitude();
            double longt= mLastLoc.getLongitude();

            Geocoder geocoder= new Geocoder(getActivity().getApplicationContext(), Locale.ENGLISH);
            try {
                List<Address> adresss=geocoder.getFromLocation(lat,longt,2);
                if(adresss!=null)
                {
                    Address fa = adresss.get(0);
                    etLocation.setText(fa.getLocality());
                }
                else
                {
                    etLocation.setText("UMMMM Not foound");
                }

            }catch (Exception e){
                Toast.makeText(getActivity().getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
