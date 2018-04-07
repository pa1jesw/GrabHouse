package com.fcproject.grabhouce;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static Dialog myDialog;
    private static SeekBar seek;
    private static TextView seektext;
    private static TextView textclose;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private ImageView imageView;
    private String txtImageName="img";
    private TextView email;
    private Uri imgUri;
    SeekBar seekprice;
    TextView textcloserate;
    TextView seektextprice,textcloseprice;
    Dialog myDialogPrice;
    Dialog Rating;
    RatingBar ratBar;
    Button btnRate;
    TextToSpeech tts;

    public static final String STORAGE_PATH="image/";
    public static  final String DATABASE_PATH="image";
    public static final int REQUEST_CODE=1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth=FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(this);

        imageView = findViewById(R.id.profile_image);
        email = findViewById(R.id.email);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        tabsPager tabspager = new tabsPager(getSupportFragmentManager(),tabLayout.getTabCount());


        final ViewPager  viewpager = findViewById(R.id.viewpager);
        viewpager.setAdapter(tabspager);
        viewpager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    public void seekbar(){

        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.range_popup);
        seek = (SeekBar) myDialog.findViewById(R.id.seek);
        seektext = (TextView) myDialog.findViewById(R.id.seektext);
        textclose = (TextView) myDialog.findViewById(R.id.textclose);

        seek.setMax(100);
        seek.setProgress(10);
        textclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                seek.setProgress(seek.getProgress());
            }
        });
        myDialog.show();
        seektext.setText("Kms:"+seek.getProgress()+"kms/"+seek.getMax()+"kms");
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seektext.setText("Kms:"+i+"kms/"+seek.getMax()+"kms");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    public void seekbarprice(){

        myDialogPrice = new Dialog(this);
        myDialogPrice.setContentView(R.layout.price_popup);
        //seekprice = (SeekBar) myDialogPrice.findViewById(R.id.seekprice);
        //seektextprice = (TextView) myDialogPrice.findViewById(R.id.seektextprice);
        textcloseprice = (TextView) myDialogPrice.findViewById(R.id.textcloseprice);

        seekprice.setMax(1000);
        seekprice.setProgress(100);
        textcloseprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialogPrice.dismiss();
                seekprice.setProgress(seekprice.getProgress());
            }
        });
        myDialogPrice.show();
        seektextprice.setText("₹:"+seekprice.getProgress()+"₹/"+seekprice.getMax()+"₹");
        seekprice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seektextprice.setText("In Lakhs: "+i+"₹/"+seekprice.getMax()+"₹");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void rating()
    {
        Rating = new Dialog(this);
        Rating.setContentView(R.layout.rate_us);
        textcloserate = (TextView) Rating.findViewById(R.id.textcloserate);
        ratBar = (RatingBar) Rating.findViewById(R.id.ratBar);
        btnRate = (Button) Rating.findViewById(R.id.btnRate);

        Rating.show();

        textcloserate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rating.dismiss();
            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.CANADA);
                tts.setPitch(1.2f);
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String rbGet = "Thank You Rating is " +String.valueOf(ratBar.getRating());
                tts.speak(rbGet,TextToSpeech.QUEUE_FLUSH,null);
                Toast.makeText(home.this, "Successfully Rated", Toast.LENGTH_SHORT).show();
                btnRate.setText("Rated");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_range) {
            seekbar();
        } else if (id == R.id.nav_price) {
            seekbarprice();
        } else if (id == R.id.nav_bookmark) {
            Intent i = new Intent(this,bookmark.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            String message = "Download share and enjoy grabing your house handsON";
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT,message);
            startActivity(i);

        } else if (id == R.id.nav_rate) {
            rating();
        }
        else if(id == R.id.nav_logout){
            progressDialog.setMessage("Logging Out");
            progressDialog.show();
            mAuth.signOut();    //logout user
            finish();   //finish current activity
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
