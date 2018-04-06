package com.fcproject.grabhouce;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LakhwaniPc on 05-04-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<String>cNames = new ArrayList<>();
    ArrayList<String>imgUrls = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<String> cNames, ArrayList<String> imgUrls) {
        this.context = context;
        this.cNames = cNames;
        this.imgUrls = imgUrls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontalrecylerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(imgUrls.get(position)).into(holder.imageView);
        holder.textView.setText(cNames.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, cNames.get(position), Toast.LENGTH_SHORT).show();
                Constants.C_NAME=cNames.get(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.Circleimg);
            textView=itemView.findViewById(R.id.name);
        }
    }

}
