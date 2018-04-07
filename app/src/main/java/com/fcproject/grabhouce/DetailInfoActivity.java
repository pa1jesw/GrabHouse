package com.fcproject.grabhouce;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailInfoActivity extends AppCompatActivity {
TextView tvTitle,tvLocation,tvPrice;
ImageView ivimg;
Button btnCall;
Bundle bundle;
Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.newdetail);
        tvTitle=findViewById(R.id.tvTitle);
        tvLocation=findViewById(R.id.tvLocation);
        tvPrice=findViewById(R.id.tvPrice);
        ivimg=findViewById(R.id.ivimg);
        btnCall=findViewById(R.id.btnCall);
      //  btnShare=findViewById(R.id.btnShare);
        bundle=getIntent().getExtras();
        tvTitle.setText(bundle.getString("title"));
        tvLocation.setText("Location: "+bundle.getString("location"));
        tvPrice.setText("Price: "+bundle.getString("price"));



     //   Glide.with(this).load(bundle.getString("imgurl")).into(ivimg);
        //final Uri link= Uri.parse(bundle.getString("produrl"));
       //     final String link=bundle.getString("produrl");
        /*btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uri = Uri.parse(bundle.getString("produrl"));
                Intent itn = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(itn);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,link);
                startActivity(Intent.createChooser(intent,"Share via.."));
            }
        }); */
    }
}
