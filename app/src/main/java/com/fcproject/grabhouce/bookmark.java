package com.fcproject.grabhouce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemLongClickListener;

public class bookmark extends AppCompatActivity {
    ListView lvbm;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        lvbm=findViewById(R.id.lvbm);
       // SharedPreferences sharedPreferences=getSharedPreferences("firstTime",0);
      //  boolean first=sharedPreferences.getBoolean("pehliBaar",false);
        final MyDatabaseHelper dbh=MyDatabaseHelper.getInstance(this);
         adapter=new ArrayAdapter(bookmark.this,android.R.layout.simple_list_item_1,dbh.getAllbookmark());
        lvbm.setAdapter(adapter);
      /*  if(first==false)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.lvbm),"Long Press",
                    "To delete saved bookmark long press the title")
            .tintTarget(true)
            .cancelable(true)
            .outerCircleColor(R.color.colorAccent),
                    new TapTargetView.Listener(){
                        @Override
                        public void onTargetClick(TapTargetView view)
                        {
                            super.onTargetClick(view);
                            Toast.makeText(bookmark.this, "You Got it!", Toast.LENGTH_SHORT).show();
                        }
                    });
            editor.putBoolean("pehliBaar",true);
            editor.commit();
        }*/


      lvbm.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedFromList = (String) lvbm.getItemAtPosition(i);
                  selectedFromList = selectedFromList.substring(0, selectedFromList.length() - 1);

                Intent replyIntent = new Intent(bookmark.this,detailtwo.class);
                replyIntent.putExtra("title", selectedFromList);
                startActivity(replyIntent);
                //setResult(RESULT_OK, replyIntent);
                //finish();

            }
        });

        lvbm.setOnItemLongClickListener(new OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id)
            {
                String title= (String)lvbm.getItemAtPosition(pos);
                String title1= title.substring(0, title.length() - 1);
                Toast.makeText(bookmark.this, title, Toast.LENGTH_SHORT).show();
               dbh.delBookmark(title1);
                adapter.remove(title);
                adapter.notifyDataSetChanged();
            return true;
            }
        });

    }


}
