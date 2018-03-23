package com.fcproject.grabhouce;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class OnBoarding extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);
        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Buy Home", "Searching for your dream home is never an easy task! Don't Worry we got you", R.drawable.home);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Rent Home", "Rent your home in the fastest way possible through GrabHouse", R.drawable.home);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Sale ", "Wan't to make some cash from selling your house? GrabHouse does it for you", R.drawable.home);

        ahoyOnboarderCard1.setBackgroundColor(R.color.white);
        ahoyOnboarderCard2.setBackgroundColor(R.color.white);
        ahoyOnboarderCard3.setBackgroundColor(R.color.white);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.grey_600);
        }

        setFinishButtonTitle("Finish");
        showNavigationControls(false);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.solid_one);
        colorList.add(R.color.solid_two);
        colorList.add(R.color.solid_three);

        setColorBackground(colorList);

//        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
//        setFont(face);

        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}
