package com.education.imagefire;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.R.attr.data;

/**
 * Created by NET LINK on 9/29/2017.
 */

public class Recycleadpater extends RecyclerView.Adapter<Recycleadpater.MyHolder> {
    List<RecyclerUpload> listdata;
    Context mContxt;

    public Recycleadpater(Context mContxt,List<RecyclerUpload> listdata) {
        this.mContxt=mContxt;
        this.listdata = listdata;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycle,parent,false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    public void onBindViewHolder(MyHolder holder, int position) {
        RecyclerUpload data = listdata.get(position);
        String dou=String.valueOf(data.getDistance());
        holder.vname.setText(data.getName());
        holder.vdistance.setText(dou);
        holder.vlike.setText(data.getLikes());
        Picasso.with(mContxt).load(data.getUri()).resize(100, 100).into(holder.uri);
      // PicassoClient.downloadImage(mContxt,listdata.get(position).getUri(),holder.uri);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView vname,vdistance,vlike;
        ImageView uri;

        public MyHolder(View itemView) {
            super(itemView);
            vname = (TextView) itemView.findViewById(R.id.name9);
            uri=(ImageView)itemView.findViewById(R.id.image_1);
            vdistance=(TextView)itemView.findViewById(R.id.distance);
            vlike=(TextView)itemView.findViewById(R.id.likes);
        }
    }

}
