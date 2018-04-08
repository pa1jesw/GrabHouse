package com.fcproject.grabhouce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db;
    private static MyDatabaseHelper mInstance = null;

    public static MyDatabaseHelper getInstance(Context ctx) {

        if (mInstance == null) {
            mInstance = new MyDatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }
    private MyDatabaseHelper(Context ctx) {
        super(ctx, "OurDatabase", null, 1);
        this.context = ctx;
    }
    /*
    public MyDatabaseHelper(Context context) {
        super(context,"OurDatabase",null,1);
        this.context = context;
        db= this.getWritableDatabase();
    }*/


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table bookmark(title text PRIMARY KEY,price text,location,img text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public void addBookmark(String title,String price,String location,String img) {
        db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("location",location);
        values.put("img",img);
        values.put("price",price);
        long rid= db.insert("bookmark",null,values);
        if(rid<0){
            Toast.makeText(context, "Insert issue!Product might be bookmared!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(context, "Insert Successful", Toast.LENGTH_SHORT).show();
        db.close();


    }

    public ArrayList getAllbookmark() {
        db= this.getWritableDatabase();
        Cursor cursor=db.query("bookmark", new String[]{"title"},null,null,null,null,null,null);
        ArrayList<String> bms=new ArrayList<>();
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            do{
                String name=cursor.getString(0);
                bms.add(name+"\n");

            }while(cursor.moveToNext());
        }
        else bms.add("No Records");
        cursor.close();
        db.close();
        return bms;
    }

    public String getPrice(String title) {
        db= this.getWritableDatabase();
        Cursor cursor=db.query("bookmark", new String[]{"price"},"title=\'"+title+"\'",null,null,null,null,null);
        cursor.moveToFirst();
        String price=cursor.getString(0);

        cursor.close();
        db.close();
        return price;
    }


    public String getLocation(String title) {
        db= this.getWritableDatabase();
        Cursor cursor=db.query("bookmark", new String[]{"location"},"title=\'"+title+"\'",null,null,null,null,null);
        cursor.moveToFirst();
        String location=cursor.getString(0);
        cursor.close();
        db.close();
        return location;
    }


    public String getImg(String title) {
        db= this.getWritableDatabase();
        Cursor cursor=db.query("bookmark", new String[]{"img"},"title=\'"+title+"\'",null,null,null,null,null);
        cursor.moveToFirst();
        String img=cursor.getString(0);

        cursor.close();
        db.close();
        return img;
    }



    public void delBookmark(String title) {
        db= this.getWritableDatabase();
     long rid=db.delete("bookmark","title=\'"+title+"\'",null);
        if(rid==0){
            Toast.makeText(context, "delelte issue", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(context, ""+rid+" rows deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }
}

