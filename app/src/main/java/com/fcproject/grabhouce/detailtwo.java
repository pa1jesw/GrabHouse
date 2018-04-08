package com.fcproject.grabhouce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class detailtwo extends AppCompatActivity {
    TextView tvTitle,tvLocation,tvPrice;
    ImageView ivimg;
    Button btnCall;
    MyDatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newdetail);
        tvTitle=findViewById(R.id.tvTitle);
        tvLocation=findViewById(R.id.tvLocation);
        tvPrice=findViewById(R.id.tvPrice);
        ivimg=findViewById(R.id.ivimg);
        btnCall=findViewById(R.id.btnCall);
        final MyDatabaseHelper dbh=MyDatabaseHelper.getInstance(this);
        Intent itc = getIntent();
        String title = itc.getStringExtra("title");
        tvTitle.setText(title);
        tvPrice.setText("Price: "+dbh.getPrice(title));
        tvLocation.setText("Location: "+dbh.getLocation(title));
        Glide.with(this).load(dbh.getImg(title)).into(ivimg);
      //  tvTitle.setText((CharSequence) list);




    }}