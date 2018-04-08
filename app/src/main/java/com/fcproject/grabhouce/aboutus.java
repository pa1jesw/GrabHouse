package com.fcproject.grabhouce;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_aboutus);
        EasySplashScreen config=new EasySplashScreen(aboutus.this)
                .withFullScreen()
                //.withTargetActivity(MainActivity.class)
                //.withSplashTimeOut(2000)
                //.withBackgroundColor(Color.parseColor("#24898e"))
                .withLogo(R.drawable.logo)
                .withHeaderText("About Grab House")
                .withFooterText("Searching for your dream home is never an easy task! Don't Worry we got you")
                .withBackgroundResource(R.drawable.onboardingcolor)
                .withBeforeLogoText("Local Property Dealing Became Easy with")
                .withAfterLogoText("Grab House");


        //setting the text color
        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.BLACK);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);


        //setting to view

        View view=config.create();

        //settng view to content view
        setContentView(view);
    }
}
