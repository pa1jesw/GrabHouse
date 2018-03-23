package com.fcproject.grabhouce;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class BeforeTabLayout extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_tab_layout);
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
    }

    public void gotoTab(View view) {
        Intent intent=new Intent(getApplicationContext(),home.class);
        startActivity(intent);
    }

    public void gotoTab3(View view) {
        Intent intent=new Intent(getApplicationContext(),home.class);
        startActivity(intent);
    }
  public void gotoTab4(View view) {
      Intent intent=new Intent(getApplicationContext(),home.class);
      startActivity(intent);

  }
  public void gotoTab1(View view) {
      Intent intent=new Intent(getApplicationContext(),home.class);
      startActivity(intent);
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            BeforeTabLayout.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    }
                }
            });
        }
    }
}