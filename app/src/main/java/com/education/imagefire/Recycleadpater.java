package com.education.imagefire;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.data;

/**
 * Created by NET LINK on 9/29/2017.
 */

public class Recycleadpater extends RecyclerView.Adapter<Recycleadpater.MyHolder> {
    ArrayList<RecyclerUpload> listdata;
    Context mContxt;

    public Recycleadpater(Context mContxt,ArrayList<RecyclerUpload> listdata) {
        this.mContxt=mContxt;
        this.listdata = listdata;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycle,parent,false);

        MyHolder myHolder = new MyHolder(view,mContxt,listdata);
        return myHolder;
    }

    public void onBindViewHolder(final MyHolder holder,final int position) {
        RecyclerUpload data = listdata.get(position);
        String dou=String.valueOf(data.getDistance());
        holder.vname.setText(data.getName());
        holder.vdistance.setText(dou);
        holder.vlike.setText(data.getLikes());
        Picasso.with(mContxt).load(data.getUri()).resize(100, 100).into(holder.uri);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder,holder.imageButton,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        TextView vname,vdistance,vlike;
        ImageView uri;
        ImageButton imageButton;

        ArrayList<RecyclerUpload> carList = new ArrayList<RecyclerUpload>();
        Context mContxt;


        public MyHolder(View itemView,Context mContxt,ArrayList<RecyclerUpload> carList) {
            super(itemView);
            this.mContxt=mContxt;
            this.carList=carList;
            itemView.setOnClickListener(this);
            vname = (TextView) itemView.findViewById(R.id.name9);
            uri=(ImageView)itemView.findViewById(R.id.image_1);
            vdistance=(TextView)itemView.findViewById(R.id.distance);
            vlike=(TextView)itemView.findViewById(R.id.likes);
            imageButton=(ImageButton)itemView.findViewById(R.id.image00);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            RecyclerUpload car = this.carList.get(position);
            Intent i12 = new Intent(this.mContxt, StudentShowActivity.class);
            i12.putExtra("Ownerid", car.getOwner_id());
            i12.putExtra("Hostelid", car.getHostel_id());
            i12.putExtra("Hostelname", car.getName());
            i12.putExtra("Hosteladdress", car.getAddress());
            i12.putExtra("uni_name", car.getUni_name());
            this.mContxt.startActivity(i12);
        }
    }

    private void showPopupMenu(final RecyclerView.ViewHolder holder, View view, final int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.pop_up, popup.getMenu());
        // popup.setOnMenuItemClickListener(new MyMenuStyle(position,holder));
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.user_profile:
                        RecyclerUpload hostel = listdata.get(position);
                        final String HostelId2 = hostel.getHostel_id();

                        AlertDialog.Builder dialog = new AlertDialog.Builder(mContxt);
                        dialog.setCancelable(false);
                        dialog.setTitle("Dialog on Android");
                        dialog.setMessage("Are you sure you want to delete this entry?");
                        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                DatabaseReference databaseReferenceB = FirebaseDatabase.getInstance().getReference("Hostels").child(HostelId2);
                                DatabaseReference databaseReferenceC = FirebaseDatabase.getInstance().getReference("HostelInformation").child(HostelId2);
                                DatabaseReference databaseReferenceD = FirebaseDatabase.getInstance().getReference("MapsInfo").child(HostelId2);
                                DatabaseReference databaseReferenceE = FirebaseDatabase.getInstance().getReference("Hostel_Property_Info").child(HostelId2);
                                DatabaseReference databaseReferenceF = FirebaseDatabase.getInstance().getReference("Facilities").child(HostelId2);

                                Toast.makeText(mContxt, "Deleting", Toast.LENGTH_SHORT).show();
                                databaseReferenceB.removeValue();
                                databaseReferenceC.removeValue();
                                databaseReferenceD.removeValue();
                                databaseReferenceE.removeValue();
                                databaseReferenceF.removeValue();
                                Toast.makeText(mContxt, "I Have deleted", Toast.LENGTH_SHORT).show();


                            }
                        })
                                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Action for "Cancel".
                                    }
                                });

                        final AlertDialog alert = dialog.create();
                        alert.show();

                        break;

                    case R.id.sign_out:
                        Toast.makeText(mContxt, "Functionality not added", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }

        });
        popup.show();
    }
}

