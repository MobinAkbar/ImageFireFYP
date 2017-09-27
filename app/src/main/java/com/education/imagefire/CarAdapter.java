package com.education.imagefire;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by NET LINK on 8/9/2017.
 */

public class CarAdapter extends ArrayAdapter<ImageUpload> {

    private Activity context;
    private List<ImageUpload> carList;
    private int resource;

    public CarAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ImageUpload> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        carList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(resource,null);
        TextView tv= (TextView) v.findViewById(R.id.textView);
        ImageView tp= (ImageView) v.findViewById(R.id.imageView);

        tv.setText(carList.get(position).getName());
        PicassoClient.downloadImage(context,carList.get(position).getUrl(),tp);

        return  v;
    }
    //
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
//        MyViewHolder holder=new MyViewHolder(v);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.name.setText(carList.get(position).getName());
//        //Picasso.with(mContext).load(ImageUpload.getUri()).into(holder.thumbnail);
//        PicassoClient.downloadImage(mContext,carList.get(position).getUrl(),holder.thumbnail);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return carList.size();
//    }
}





