package com.education.imagefire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewActivity extends AppCompatActivity {
    TextView name,log,lat;
    //Button save,view;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<RecyclerUpload> list;
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerview = (RecyclerView) findViewById(R.id.recycle);
       // myRef=FirebaseDatabase.getInstance().getReference(MainActivity.FB_DATABASE_PATH);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Image");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        list = new ArrayList<>();
                        // StringBuffer stringbuffer = new StringBuffer();
                        for(DataSnapshot s :dataSnapshot.getChildren()){

                            ImageUpload userdetails = s.getValue(ImageUpload.class);
                            RecyclerUpload listdata = new RecyclerUpload();

                            String name=userdetails.getName();
                            String uri=userdetails.getUrl();
                           // double log=userdetails.getLongi();
                            //Toast.makeText(RecyclerviewActivity.this,"Valu is"+name,Toast.LENGTH_LONG).show();
                           // double lat=userdetails.getLat();
                            //double logi=userdetails.getLongi();
                            listdata.setName(name);
                            listdata.setUri(uri);
                            //listdata.setLongi(log);

                           // listdata.setLat(lat);
                            //listdata.setLongi(logi);
                            list.add(listdata);
                            // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                        }

                        //Recycleadpater recycler = new Recycleadpater(RecyclerviewActivity.this,list);
                        //RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(RecyclerviewActivity.this);
                        //recyclerview.setLayoutManager(layoutmanager);
                        //recyclerview.setItemAnimator( new DefaultItemAnimator());
                        //recyclerview.setAdapter(recycler);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //  Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

            }
}

