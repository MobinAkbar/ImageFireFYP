package com.education.imagefire;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by NET LINK on 1/2/2018.
 */

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.MyHolder>  {
    ArrayList<Notification> listdata;
    Context mContxt;

    public NotifyAdapter(Context mContxt,ArrayList<Notification> listdata) {
        this.mContxt=mContxt;
        this.listdata = listdata;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        MyHolder myHolder = new MyHolder(view,mContxt,listdata);
        return myHolder;
    }

    public void onBindViewHolder(final MyHolder holder, final int position) {
        Notification data = listdata.get(position);
        final String id=data.getId();
        holder.vname.setText(data.getName());
        holder.vtype.setText(data.getType());
        holder.vtime.setText(data.getData());
        holder.vdate.setText(data.getTime());
        Picasso.with(mContxt).load(data.getUrl()).into(holder.uri);
        holder.vtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.vtype.setText("Accepted");
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                DatabaseReference   databaseReference2 = FirebaseDatabase.getInstance().getReference("Notifications").child(id);
                databaseReference2.child("time").setValue(currentDateTimeString);
                databaseReference2.child("type").setValue("accepted").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ((NotificationActivity)mContxt).restsrt();
                    }
                });
            }
        });

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


    static class MyHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        TextView vname,vtype,vtime,vdate;
        ImageView uri;
        ImageButton imageButton;

        ArrayList<Notification> carList = new ArrayList<Notification>();
        Context mContxt;


        public MyHolder(View itemView,Context mContxt,ArrayList<Notification> carList) {
            super(itemView);
            this.mContxt=mContxt;
            this.carList=carList;
            itemView.setOnClickListener(this);
            vname = (TextView) itemView.findViewById(R.id.namenot);
            uri=(ImageView)itemView.findViewById(R.id.profilenot);
            vtype=(TextView)itemView.findViewById(R.id.requestnot);
            vtime=(TextView)itemView.findViewById(R.id.timenot);
            vdate=(TextView)itemView.findViewById(R.id.timing);
            imageButton=(ImageButton)itemView.findViewById(R.id.imagenot);
        }

        @Override
        public void onClick(View view) {


        }
    }

    private void showPopupMenu(final RecyclerView.ViewHolder holder, View view, final int position) {
        // inflate menu

        PopupMenu popup = new PopupMenu(view.getContext(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.delete, popup.getMenu());
        // popup.setOnMenuItemClickListener(new MyMenuStyle(position,holder));
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        Notification hostel = listdata.get(position);
                        final String owner=hostel.getId();
                        //final String HostelId2 = hostel.getHostel_id();

                        AlertDialog.Builder dialog = new AlertDialog.Builder(mContxt);
                        dialog.setCancelable(false);
                        dialog.setTitle("Dialog on Android");
                        dialog.setMessage("Are you sure you want to delete this entry?");
                        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                DatabaseReference databaseReferenceC = FirebaseDatabase.getInstance().getReference("Notifications").child(owner);
                                databaseReferenceC.removeValue();
                                Toast.makeText(mContxt, "Deleted", Toast.LENGTH_SHORT).show();

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
                }
                return true;
            }

        });
        popup.show();
    }

}
