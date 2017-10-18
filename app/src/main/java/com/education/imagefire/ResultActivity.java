package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<ImageUpload> imglistt;
    private ListView lv;
    private ProgressDialog progressDialog;
    private CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imglistt=new ArrayList<>();
        lv=(ListView)findViewById(R.id.recyclerView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ip=new Intent(ResultActivity.this,ShowdataActivity.class);
                startActivity(ip);
            }
        });

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent ip=new Intent(ResultActivity.this,MapsActivity.class);
//                startActivity(ip);
//            }
//        });

        String value=getIntent().getStringExtra("name");
        Toast.makeText(ResultActivity.this, "i have" +value , Toast.LENGTH_SHORT).show();

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseDatabaseReference.child("Image").orderByChild("name").equalTo(value);


        final ValueEventListener valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    imglistt.add(postSnapshot.getValue(ImageUpload.class));
                    //TODO get the data here

                }
                adapter=new CarAdapter(ResultActivity.this,R.layout.list_item,imglistt);
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        };

        query.addValueEventListener(valueEventListener);


    }
}
