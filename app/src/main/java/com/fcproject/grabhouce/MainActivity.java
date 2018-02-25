package com.fcproject.grabhouce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {

    Button btnCreate,btnLogin,btnGoogle;
    EditText etEmail,etPassword;
    TextView tvForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnCreate);
        btnGoogle = findViewById(R.id.btnGoogle);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvForgot = findViewById(R.id.tvForgot);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this,create_account.class);
                startActivity(i1);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= String.valueOf(etEmail.getText());
                String pass= String.valueOf(etPassword.getText());

                if(email.length()!=0 && pass.length()!=0)
                {
                    Intent i2 = new Intent(MainActivity.this, home.class);
                    startActivity(i2);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER THE DETAILS",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
