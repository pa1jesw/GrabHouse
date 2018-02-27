package com.fcproject.grabhouce;


import android.content.Intent;

import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    EditText etEmail, etPassword;
    int RC_SIGN_IN=234;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        btnSignup = findViewById(R.id.btnSignUp);
        btnCancel = findViewById(R.id.btnCancel);
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(etEmail.getText());
                String pass = String.valueOf(etPassword.getText());

                if (email.length() != 0 && pass.length() != 0) {
                    Intent i = new Intent(create_account.this, tabsPager.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER THE DETAILS",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.btnGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etEmail.getText().clear();
                etPassword.getText().clear();
            }
        });

    }//on create close



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

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(create_account.this,"Authetication Failed",Toast.LENGTH_LONG);
                            Log.d("Failed","Failed",task.getException());
                        }


                    }
                });


    }
    private void signIn()
    {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

}

















