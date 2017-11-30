package com.education.imagefire;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by NET LINK on 11/27/2017.
 */

public class Pager extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    //private Integer[] images={R.drawable.celll2,R.drawable.h11,R.drawable.way};
    private ArrayList<String> images;

    public Pager(Context context,ArrayList<String> images) {
        this.context = context;
        this.images=images;

    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imag23);
       // imageView.setImageResource(images.get(position));
        String uri=images.get(position);
        PicassoClient.downloadImage(context,uri,imageView);

        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp=(ViewPager) container;
        View view=(View) object;
        vp.removeView(view);
    }
}
