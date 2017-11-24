package com.education.imagefire;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by NET LINK on 8/31/2017.
 */

public class PicassoClient {
    public static void downloadImage(Context c, String uri, ImageView img){

        if(uri!=null && uri.length()>0){
            Picasso.with(c).load(uri).placeholder(R.drawable.ques_1).into(img);
            //Toast.makeText(c, "Downloading", Toast.LENGTH_SHORT).show();
        }else{
            Picasso.with(c).load(R.drawable.afridi).fit().centerCrop().resize(80,80).into(img);
        }
    }

}
