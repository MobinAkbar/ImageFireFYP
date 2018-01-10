package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewActivity extends AppCompatActivity{

    RecyclerView recyclerview;
    private DatabaseReference databaseReference;
    private List<University> imglist;
    private ArrayList<RecyclerUpload> hostelList;
    private ListView lv;
    private String hostelid;
    int i;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId;
    String uniname, sexx, bhabi;
    double latii,longii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid();
       // Toast.makeText(ResultActivity.this, "Valu is" + UserId, Toast.LENGTH_SHORT).show();

        uniname = getIntent().getStringExtra("name");
        sexx = getIntent().getStringExtra("sex");

        //Toast.makeText(ResultActivity.this, "Name is " + uniname, Toast.LENGTH_SHORT).show();
        //Toast.makeText(ResultActivity.this, "Gender is " + sexx, Toast.LENGTH_SHORT).show();

        imglist = new ArrayList<>();
        hostelList = new ArrayList<>();
        recyclerview = (RecyclerView) findViewById(R.id.recycle);
        recyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query2 = mFirebaseDatabaseReference.child("Universities").orderByChild("name").equalTo(uniname);
        final ValueEventListener eventListener1=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                   NearBy university=ds.getValue(NearBy.class);
                   latii=university.getLatitude();
                   longii=university.getLongitude();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query2.addValueEventListener(eventListener1);
   Toast.makeText(RecyclerviewActivity.this, "I have " + latii, Toast.LENGTH_SHORT).show();

        String value = getIntent().getStringExtra("name");
       // Toast.makeText(ResultActivity.this, "i have" + value, Toast.LENGTH_SHORT).show();

        for (i = 0; i < 3; i++) {

            if (i == 0) {
                bhabi = "university_1";
            }
            if (i == 1) {
                bhabi = "university_2";
            }
            if (i == 2) {
                bhabi = "university_3";
            }

            DatabaseReference mFirebaseDatabaseReference1 = FirebaseDatabase.getInstance().getReference();
            Query query111 = mFirebaseDatabaseReference1.child("Hostel_Universities").orderByChild(bhabi).equalTo(value);

            final ValueEventListener eventListener11 = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        University propertyInfo = ds.getValue(University.class);
                        hostelid = propertyInfo.getId();
                        //Toast.makeText(ResultActivity.this, "here is " + hostelid, Toast.LENGTH_SHORT).show();
                        imglist.add(propertyInfo);

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        Query query2 = databaseReference.child("Hostels").orderByChild("id").equalTo(hostelid);
                       // Toast.makeText(ResultActivity.this, " I HAVE SOMETHING 3", Toast.LENGTH_SHORT).show();
                        final ValueEventListener eventListener2 = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    Hostel hostel = ds.getValue(Hostel.class);
                                    String hostelId=hostel.getId();
                                    String ownerId=hostel.getOwner();
                                    String name = hostel.getName();
                                    String adres=hostel.getAddres();
                                    String like = hostel.getLikes();
                                    String uri = hostel.getUri();
                                    double lat = hostel.getLatitude();
                                    double longi = hostel.getLongitude();
                                    String uni=uniname;
                                    double dist = distance(lat, longi, latii, longii);
                                    RecyclerUpload obj = new RecyclerUpload(hostelId,ownerId,name,adres,uni, uri, like, dist);
                                    hostelList.add(obj);
                                }

                                Recycleadpater recycler = new Recycleadpater(RecyclerviewActivity.this,hostelList);
                                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(RecyclerviewActivity.this);
                                recyclerview.setLayoutManager(layoutmanager);
                                recyclerview.setItemAnimator( new DefaultItemAnimator());
                                recyclerview.setAdapter(recycler);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        };
                        query2.addValueEventListener(eventListener2);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            query111.addValueEventListener(eventListener11);
        }

    }
    public double distance (double lat_a, double lng_a, double lat_b, double lng_b )
    {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        int meterConversion = 1609;

        return new Double(distance * meterConversion).doubleValue();
    }

}

