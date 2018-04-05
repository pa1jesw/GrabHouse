package com.fcproject.grabhouce;


import android.app.ProgressDialog;
import android.content.Intent;

import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class create_account extends AppCompatActivity {

    Button btnSignup, btnCancel, btnFacebook;

    GoogleSignInClient mGoogleSignInClient;
    EditText etEmail, etPassword,et_email,et_pass;
    private ProgressDialog progressDialog;
    int RC_SIGN_IN=234;
    private FirebaseAuth mAuth;
    String email,password;
    FirebaseUser currentuser;
    ActionProcessButton btnFirebaseSignUp,btnFirebaseLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        btnFirebaseSignUp = findViewById(R.id.btnFirebaseSignUp);
        btnFirebaseSignUp.setMode(ActionProcessButton.Mode.ENDLESS);
        //start with progress = 0
        btnFirebaseSignUp.setProgress(0);
        et_email=findViewById(R.id.ed_email);
        et_pass=findViewById(R.id.ed_password);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
//        etEmail = findViewById(R.id.etEmail);
//        etPassword = findViewById(R.id.etPassword);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



     //   btnSignup.setOnClickListener(this);

//        findViewById(R.id.btnGoogle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//
//            }
//        });

//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                etEmail.getText().clear();
//                etPassword.getText().clear();
//            }
//        });
//
//    }//on create close
//
//    public void onClick(View view){
//        if(view == btnSignup){
//            registerUser();
//        }
    }

//    private void registerser(View view) {
////        String email = etEmail.getText().toString().trim();
////        String password = etPassword.getText().toString().trim();
////        if(TextUtils.isEmpty(email)){
////            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
////            return;
////        }
////
////        if(TextUtils.isEmpty(password)){
////            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
////            return;
////        }
////
////        //if the email and password are not empty
////        //displaying a progress dialog
////
////        progressDialog.setMessage("Registering You");
////        progressDialog.show();
////
////        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
////            @Override
////            public void onComplete(@NonNull Task<AuthResult> task) {
////                if(task.isSuccessful()){
////                    Toast.makeText(create_account.this, "Success ! Start Exploring", Toast.LENGTH_LONG).show();
////                    startActivity(new Intent(getApplicationContext(),home.class));
////                }
////                else{
////                    Toast.makeText(create_account.this, "Registration Error", Toast.LENGTH_SHORT).show();
////                }
////                progressDialog.dismiss();
////            }
////        });
//        email =et_email.getText().toString();
//        password=et_pass.getText().toString();
//
//        if(password.length()<8)
//        {
//            et_pass.setError("Please enter password of minimum length 8");
//            et_pass.requestFocus();
//            return;
//        }
//        else if(TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
//        {
//            et_email.setError("Enter Valid Email id");
//            et_email.requestFocus();
//            return;
//        }
//        else
//        {
//            ActionProcessButton btn = (ActionProcessButton) v;
//            // we add 25 in the button progress each click
//            if(btn.getProgress() < 100){
//                btn.setProgress(btn.getProgress() + 15);
//                et_email.setEnabled(false);
//                et_pass.setEnabled(false);
//            }
//            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
////                            if(currentuser!=null)
////                            {
////                                Toast.makeText(LoginActivity.this, "Account created successfully!  with email Id"+email, Toast.LENGTH_SHORT).show();
////                            }
//                    if(task.isSuccessful())
//                    {
//                        currentuser=mAuth.getCurrentUser();
//                        String userEmail=currentuser.getEmail();
//                        Toast.makeText(getApplicationContext(), "Account created successfully!  with email Id"+ userEmail, Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(getApplicationContext(),home.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    else{
//                        Toast.makeText(getApplicationContext(), "Error While Creating Account with email id "+task.getException(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }


    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
            super.onActivityResult(requestCode, resultCode,data);


            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
                }
            }
        }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("ABC", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("ABC1", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(create_account.this,"SIGN IN SUCCESSFULLY",Toast.LENGTH_LONG);
                            startActivity(new Intent(getApplicationContext(),BottomNavActivityy.class));
                        } else {
                            // If sign in fails , display a message to the user.
                            Toast.makeText(create_account.this,"Authetication Failed",Toast.LENGTH_LONG);
                            Log.d("Failed","Failed",task.getException());
                        }
                    }
                });
    }
//    private void GooglesignIn()
//    {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }

    public void GooglesignIn(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void registerUser(View view) {
        email =et_email.getText().toString();
        password=et_pass.getText().toString();

        if(password.length()<8)
        {
            et_pass.setError("Please enter password of minimum length 8");
            et_pass.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            et_email.setError("Enter Valid Email id");
            et_email.requestFocus();
            return;
        }
        else
        {
            ActionProcessButton btn = (ActionProcessButton) view;
            // we add 25 in the button progress each click
            if(btn.getProgress() < 100){
                btn.setProgress(btn.getProgress() + 15);
                et_email.setEnabled(false);
                et_pass.setEnabled(false);
            }
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(currentuser!=null)
//                            {
//                                Toast.makeText(LoginActivity.this, "Account created successfully!  with email Id"+email, Toast.LENGTH_SHORT).show();
//                            }
                    if(task.isSuccessful())
                    {
                        currentuser=mAuth.getCurrentUser();
                        String userEmail=currentuser.getEmail();
                        Toast.makeText(getApplicationContext(), "Account created successfully!  with email Id"+ userEmail, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),BottomNavActivityy.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error While Creating Account with email id "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

















