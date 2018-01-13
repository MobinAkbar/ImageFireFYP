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

import static android.R.attr.resource;
import static android.support.v7.widget.AppCompatDrawableManager.get;
import static com.education.imagefire.R.drawable.hostel;

/**
 * Created by NET LINK on 8/9/2017.
 */

public class CarAdapter {

//    private Activity context;
//    List<Hostel> carList;;
//
//    public CarAdapter(Activity context, List<Hostel> carList) {
//        super(context, R.layout.list_item,carList);
//        this.context=context;
//        this.carList=carList;
//    }
//
//    @Override
//    public View getView(int position,View convertView, ViewGroup parent) {
//        LayoutInflater inflater=context.getLayoutInflater();
//        final View v=inflater.inflate(R.layout.list_item,null,true);
//
//        TextView tv= (TextView) v.findViewById(R.id.text_1);
//        ImageView tp= (ImageView) v.findViewById(R.id.image_1);
//        //ImageButton img=(ImageButton)v.findViewById(R.id.img_btn);
//
//        Hostel hostel=carList.get(position);
//        tv.setText(hostel.getName());
//        PicassoClient.downloadImage(context,hostel.getUri(),tp);
//
////        img.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                PopupMenu popup = new PopupMenu(getContext(), view);
////                //Inflating the Popup using xml file
////                popup.getMenuInflater().inflate(R.menu.pop_up, popup.getMenu());
////
////                //registering popup with OnMenuItemClickListener
////                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
////                    public boolean onMenuItemClick(MenuItem item) {
////                        Toast.makeText(getContext(),"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
////                        return true;
////                    }
////                });
////
////                popup.show();//showing popup menu
////            }
////        });
//
//        return  v;
//    }

}





