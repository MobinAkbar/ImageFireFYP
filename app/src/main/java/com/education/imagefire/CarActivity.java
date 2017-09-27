package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;
import static com.education.imagefire.R.id.recyclerView;

public class CarActivity extends AppCompatActivity {

//    private DatabaseReference databaseReference;
//    private RecyclerView recyclerView;
//    //private FirebaseRecyclerAdapter<ImageUpload> adapter;
//
//
//DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
    private DatabaseReference databaseReference;
    private List<ImageUpload> imglist;
    private ListView lv;
    private ProgressDialog progressDialog;
    private CarAdapter adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        imglist=new ArrayList<>();
        lv=(ListView)findViewById(R.id.recyclerView);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("uploading");
        progressDialog.show();

        databaseReference=FirebaseDatabase.getInstance().getReference(MainActivity.FB_DATABASE_PATH);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ip=new Intent(CarActivity.this,MapsActivity.class);
                startActivity(ip);
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                       progressDialog.dismiss();
                for(DataSnapshot s:dataSnapshot.getChildren()){
                    ImageUpload im=s.getValue(ImageUpload.class);
                    imglist.add(im);

                }

                adapter=new CarAdapter(CarActivity.this,R.layout.list_item,imglist);
                lv.setAdapter(adapter);
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                   progressDialog.dismiss();
            }
        });


//
    }
//
//        recyclerView = (RecyclerView)  findViewById(recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        //DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
//        FirebaseRecyclerAdapter<ImageUpload,MyViewHolder> adapter=new FirebaseRecyclerAdapter<ImageUpload, MyViewHolder>(
//                ImageUpload.class,R.layout.list_item,MyViewHolder.class,ref
//        ) {
//            @Override
//            protected void populateViewHolder(MyViewHolder viewHolder, ImageUpload model, int position) {
//               // viewHolder.setName(model.getName());
//                //viewHolder.setUri(model.getUrl());
//                viewHolder.name.setText(model.getName());
//                //Picasso.with(mContext).load(ImageUpload.getUri()).into(holder.thumbnail);
//                PicassoClient.downloadImage(getApplicationContext(),model.getUrl(),viewHolder.thumbnail);
//
//            }
//        };
//
//        recyclerView.setAdapter(adapter);


}
