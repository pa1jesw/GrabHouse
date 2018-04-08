package com.fcproject.grabhouce;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class rentAdapter extends RecyclerView.Adapter<rentAdapter.ViewHolder> {


    Context context;
    List<Upload> uploads;

    final MyDatabaseHelper dbh;

    public rentAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;
        dbh=MyDatabaseHelper.getInstance(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_rent, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Upload upload = uploads.get(position);

        holder.tvTitle.setText("Title: " + upload.getTitle());
        holder.title=upload.getTitle();
        holder.tvPrice.setText("Price: " + upload.getPrice());
        holder.price=upload.getPrice();
        holder.tvLocation.setText("Location: " + upload.getLocation());
        holder.location = upload.getLocation();
        holder.number=upload.getNumber();
        Glide.with(context).load(upload.getUrl()).into(holder.ivImage);
        holder.img=upload.getUrl();

        ArrayList<String> marksbook=new ArrayList<String>(dbh.getAllbookmark());
        Iterator<String> itc=marksbook.iterator();
        while(itc.hasNext()){
            String abc=itc.next();
            abc = abc.substring(0, abc.length() - 1);
            if(abc.equals(holder.title)){
                holder.flag=0;
                holder.btn.setBackgroundResource(R.drawable.ic_star_black_24dp);
                break;
            }
        }


        //call not working because of runtime error
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cintent = new Intent(Intent.ACTION_DIAL);
                cintent.setData(Uri.parse("tel:" + holder.number));
                //if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                context.startActivity(cintent);
                return;
            //}
            }
        });


    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvPrice,tvLocation;
        String number;
        Button btnCall;
        ImageView ivImage;
        Button btn;
        String title,price,location,img;
        int flag;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvLocation=itemView.findViewById(R.id.tvLocation);
            btnCall=itemView.findViewById(R.id.btnCall);
            ivImage=itemView.findViewById(R.id.ivImage);
            btn=itemView.findViewById(R.id.bookmark);
            flag=1;


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(flag==1)
                    {
                        //button.setBackgroundColor(Color.CYAN);
                        btn.setBackgroundResource(R.drawable.ic_star_black_24dp);
                        Toast.makeText(context.getApplicationContext(),
                                "set bookmark", Toast.LENGTH_SHORT).show();
                        dbh.addBookmark(title,price,location,img);
                        flag=0;
                    }
                    else
                    {
                        btn.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
                        //  Toast.makeText(context.getApplicationContext(), "cancel bookmark", Toast.LENGTH_SHORT).show();
                        dbh.delBookmark(title);
                        flag=1;
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putString("title", title);
                    bundle.putString("price",price);
                    bundle.putString("location",location);
                    bundle.putString("img",img);
                    Intent intent = new Intent(context, DetailInfoActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }
    }
}
