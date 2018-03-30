package com.fcproject.grabhouce;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class rentAdapter extends RecyclerView.Adapter<rentAdapter.ViewHolder> {


    Context context;
    List<Upload> uploads;

    public rentAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;
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
        holder.tvPrice.setText("Price: " + upload.getPrice());
        holder.tvLocation.setText("Location: " + upload.getLocation());
        holder.number = upload.getNumber();
        Glide.with(context).load(upload.getUrl()).into(holder.ivImage);

     /*
        call not working because of runtime error
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + holder.number));
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    context.startActivity(intent);
                    return;
                }
            }
        });
*/

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

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvLocation=itemView.findViewById(R.id.tvLocation);
            btnCall=itemView.findViewById(R.id.btnCall);
            ivImage=itemView.findViewById(R.id.ivImage);

        }
    }
}
