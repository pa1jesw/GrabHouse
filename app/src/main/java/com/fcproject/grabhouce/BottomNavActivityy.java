package com.fcproject.grabhouce;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BottomNavActivityy extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    //code of fragment
                    setTitle("Home");
                    PageOne fragment=new PageOne();
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content,fragment,"FragmentName");
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);

                    setTitle("Purchase");
                    PageTwo fragment2=new PageTwo();
                    FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.content,fragment2,"FragmentName");
                    fragmentTransaction2.commit();
                    return true;

                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);

                    setTitle("Sell");
                    PageThree fragment3=new PageThree();
                    FragmentTransaction fragmentTransaction3=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.content,fragment3,"FragmentName");
                    fragmentTransaction3.commit();
                    return true;

                     case R.id.navigation_rent:
                    //mTextMessage.setText(R.string.title_notifications);

                         setTitle("Rent");
                         PageFour fragment4=new PageFour();
                         FragmentTransaction fragmentTransaction4=getSupportFragmentManager().beginTransaction();
                         fragmentTransaction4.replace(R.id.content,fragment4,"FragmentName");
                         fragmentTransaction4.commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_activityy);

        List<Fragment> fragments = new ArrayList<>(4);
        fragments.add(PageOne.newInstance(0));
        fragments.add(PageTwo.newInstance(0));
        fragments.add(PageThree.newInstance(0));
        fragments.add(PageFour.newInstance(0));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

      setTitle("Purchase");
        PageOne fragment2=new PageOne();
        FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.content,fragment2,"FragmentName");
        fragmentTransaction2.commit();
    }

}
