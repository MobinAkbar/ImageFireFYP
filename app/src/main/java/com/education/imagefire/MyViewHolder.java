package com.education.imagefire;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    ImageView thumbnail;

    public MyViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.textView);
        thumbnail = (ImageView) itemView.findViewById(R.id.imageView);
    }


}
