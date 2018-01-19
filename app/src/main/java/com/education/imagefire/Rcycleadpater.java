package com.education.imagefire;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;

/**
 * Created by NET LINK on 1/2/2018.
 */

public class Rcycleadpater extends RecyclerView.Adapter<Rcycleadpater.MyHolder>  {
    ArrayList<RecyclerUpload2> listdata;
    Context mContxt;

    public Rcycleadpater(Context mContxt,ArrayList<RecyclerUpload2> listdata) {
        this.mContxt=mContxt;
        this.listdata = listdata;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list2_item_recycle,parent,false);

        MyHolder myHolder = new MyHolder(view,mContxt,listdata);
        return myHolder;
    }

    public void onBindViewHolder(final MyHolder holder, final int position) {
        RecyclerUpload2 data = listdata.get(position);
        holder.vname.setText(data.getName());
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


    static class MyHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        TextView vname,vtype,vlike;
        ImageView uri;
        ImageButton imageButton;

        ArrayList<RecyclerUpload2> carList = new ArrayList<RecyclerUpload2>();
        Context mContxt;


        public MyHolder(View itemView,Context mContxt,ArrayList<RecyclerUpload2> carList) {
            super(itemView);
            this.mContxt=mContxt;
            this.carList=carList;
            itemView.setOnClickListener(this);
            vname = (TextView) itemView.findViewById(R.id.name9);
            uri=(ImageView)itemView.findViewById(R.id.image_1);
            vlike=(TextView)itemView.findViewById(R.id.likes);
            imageButton=(ImageButton)itemView.findViewById(R.id.image00);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            RecyclerUpload2 car = this.carList.get(position);
            Intent i12 = new Intent(this.mContxt, InterfaceActivity.class);
            i12.putExtra("Hostelid", car.getHostel_id());
            i12.putExtra("Hostelname", car.getName());
            i12.putExtra("Hosteladdress", car.getAddress());
            i12.putExtra("Hostelimage", car.getUri());
            i12.putExtra("Hosteltype", car.getType());
            i12.putExtra("Hostellikes", car.getLikes());
            this.mContxt.startActivity(i12);
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
                        RecyclerUpload2 hostel = listdata.get(position);
                        final String HostelId2 = hostel.getHostel_id();

                        AlertDialog.Builder dialog = new AlertDialog.Builder(mContxt);
                        dialog.setCancelable(false);
                        dialog.setTitle("Dialog on Android");
                        dialog.setMessage("Are you sure you want to delete this entry?");
                        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {


                                DatabaseReference databaseReferenceC = FirebaseDatabase.getInstance().getReference("Facilities").child(HostelId2);
                                DatabaseReference databaseReferenceD = FirebaseDatabase.getInstance().getReference("NearbyPlaces").child(HostelId2);
                                DatabaseReference databaseReferenceE = FirebaseDatabase.getInstance().getReference("Hostel_Property_Info").child(HostelId2);
                                DatabaseReference databaseReferenceF = FirebaseDatabase.getInstance().getReference("Rooms").child(HostelId2);
                                DatabaseReference databaseReferenceG = FirebaseDatabase.getInstance().getReference("Hostel_Like").child(HostelId2);
                                DatabaseReference databaseReferenceH = FirebaseDatabase.getInstance().getReference("Rooms").child(HostelId2);
                                //DatabaseReference databaseReferenceI = FirebaseDatabase.getInstance().getReference("AllHostelImages").child(HostelId2);
                                DatabaseReference databaseReferenceJ = FirebaseDatabase.getInstance().getReference("Hostel_Universities").child(HostelId2);



                                DatabaseReference mFirebaseDatabaseReference2 = FirebaseDatabase.getInstance().getReference();
                                Query query2 = mFirebaseDatabaseReference2.child("Hostels").orderByChild("id").equalTo(HostelId2);

                                final ValueEventListener eventListener2=new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                                           Hostel facilities=ds.getValue(Hostel.class);
                                            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance().getReference().getStorage();
                                            StorageReference photoRef = firebaseStorage.getReferenceFromUrl(facilities.getUri());

                                            photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(mContxt, "Deleted Succesfully", Toast.LENGTH_LONG).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception exception) {
                                                    Toast.makeText(mContxt, "Deletion failed", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                            DatabaseReference databaseReferenceB = FirebaseDatabase.getInstance().getReference("Hostels").child(HostelId2);
                                            databaseReferenceB.removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                };
                                query2.addValueEventListener(eventListener2);

                                DatabaseReference mFirebaseDatabaseReference4 = FirebaseDatabase.getInstance().getReference();
                                Query query4 = mFirebaseDatabaseReference4.child("AllHostelImages").orderByChild("ids").equalTo(HostelId2);

                                final ValueEventListener eventListener4=new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                                           HostelImages hostelImages=ds.getValue(HostelImages.class);
                                           String ans="";
                                            DatabaseReference databaseReferenceI = FirebaseDatabase.getInstance().getReference("AllHostelImages").child(HostelId2);
                                           for(int i=0;i<6;i++){

                                               if(i==0){
                                                   ans=hostelImages.getUri_1();
                                               }
                                               if(i==1){
                                                   ans=hostelImages.getUri_2();
                                               }
                                               if(i==2){
                                                   ans=hostelImages.getUri_3();
                                               }
                                               if(i==3){
                                                   ans=hostelImages.getUri_4();
                                               }
                                               if(i==4){
                                                   ans=hostelImages.getUri_5();
                                               }
                                               if(i==5){
                                                   ans=hostelImages.getUri_6();
                                               }

                                            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance().getReference().getStorage();
                                            StorageReference photoRef = firebaseStorage.getReferenceFromUrl(ans);

                                            photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(mContxt, "Deleted Succesfully", Toast.LENGTH_LONG).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception exception) {
                                                    Toast.makeText(mContxt, "Deletion failed", Toast.LENGTH_LONG).show();
                                                }
                                            });

                                           }
                                            databaseReferenceI.removeValue();
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                };
                                query4.addValueEventListener(eventListener4);


                                Toast.makeText(mContxt, "Deleting", Toast.LENGTH_SHORT).show();
                                databaseReferenceC.removeValue();
                                databaseReferenceD.removeValue();
                                databaseReferenceE.removeValue();
                                databaseReferenceF.removeValue();
                                databaseReferenceG.removeValue();
                                databaseReferenceH.removeValue();
                                //databaseReferenceI.removeValue();
                                databaseReferenceJ.removeValue();
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
                }
                return true;
            }

        });
        popup.show();
    }
}
