package com.education.imagefire;

import android.content.Context;
import android.content.DialogInterface;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NET LINK on 1/19/2018.
 */

public class Notify1Adapter extends RecyclerView.Adapter<Notify1Adapter.MyHolder> {
    ArrayList<Notification> listdata;
    Context mContxt;

    public Notify1Adapter(Context mContxt,ArrayList<Notification> listdata) {
        this.mContxt=mContxt;
        this.listdata = listdata;
    }

    @Override
    public Notify1Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list1item,parent,false);

        Notify1Adapter.MyHolder myHolder = new Notify1Adapter.MyHolder(view,mContxt,listdata);
        return myHolder;
    }

    public void onBindViewHolder(final Notify1Adapter.MyHolder holder, final int position) {
        Notification data = listdata.get(position);
        final String id=data.getId();
        holder.vname.setText(data.getName());
        holder.vtype.setText(data.getType());
        holder.vtime.setText(data.getData());
        holder.vdate.setText(data.getTime());
        Picasso.with(mContxt).load(data.getUrl()).into(holder.uri);

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
                                Toast.makeText(mContxt, "Removed", Toast.LENGTH_SHORT).show();


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
