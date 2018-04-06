package com.fcproject.grabhouce;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageTwo extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    DatabaseReference mDatabase;
    List<Upload> uploads;
    ProgressDialog progressDialog;

    public static PageTwo newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        PageTwo pageTwo = new PageTwo();
        pageTwo.setArguments(args);
        return pageTwo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page_two, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        uploads = new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog.dismiss();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    if(upload.getSelection().equals("Sell")) {
                        if(Integer.parseInt(upload.getPrice()) >= Constants.MIN_PLACE_PRICE && Integer.parseInt(upload.getPrice()) <= Constants.MAX_PLACE_PRICE   ){
                       if(Constants.C_NAME.equals(""))
                            uploads.add(upload);
                       else if(upload.getLocation().equals(Constants.C_NAME))
                           uploads.add(upload);

                }}
                }
                //creating adapter
                Context context=getActivity();
                adapter = new rentAdapter(context, uploads);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        return view;
    }

}
