package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OwnerShowActivity extends AppCompatActivity {

    ImageView image;
    private TextView prop;
    private ImageView imageView;
    private TextView see;
    private TextView name;
    private TextView address;
    private TextView value;
    private TextView security;
    private TextView location;
    private TextView staff;
    private TextView onames;
    private TextView onumbrs1;
    private TextView onumbr2;
    private TextView oemails;
    private TextView rooms1;
    private TextView beds1;

    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private HostelInfo hostelInfo;
    private Map map;
    private PropertyInfo propertyInfo;
    private Facilities facilities;
    private Owner owner;
    private double latitude12;
    private double longitude13;
    private double m_lat;
    private double m_logi;
    String lati_0;
    String longi_0;
    private String nameMap;
    String ltitude;
    String logitude;
    String univrsty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_show);


        final String ownerid=getIntent().getStringExtra("Ownerid");
        final String hostlid=getIntent().getStringExtra("Hostelid");
        String hostlname=getIntent().getStringExtra("Hostelname");
        String hostluri=getIntent().getStringExtra("Hosteluri");
        univrsty=getIntent().getStringExtra("uni_name");

        Toast.makeText(OwnerShowActivity.this,"Value is"+hostlid,Toast.LENGTH_SHORT).show();
        Toast.makeText(OwnerShowActivity.this,"Value is"+hostlname,Toast.LENGTH_SHORT).show();
        Toast.makeText(OwnerShowActivity.this,"Value is"+hostluri,Toast.LENGTH_SHORT).show();

        see=(TextView)findViewById(R.id.seeall);
        image=(ImageView)findViewById(R.id.image);
        name=(TextView)findViewById(R.id.name);
        address=(TextView)findViewById(R.id.address);
        value=(TextView)findViewById(R.id.value);
        security=(TextView)findViewById(R.id.security);
        location=(TextView)findViewById(R.id.location);
        staff=(TextView)findViewById(R.id.staf);
        imageView=(ImageView)findViewById(R.id.mapid);
        prop=(TextView)findViewById(R.id.properr);
        onames=(TextView)findViewById(R.id.oname);
        oemails=(TextView)findViewById(R.id.oemail);
        onumbrs1=(TextView)findViewById(R.id.onumb1);
        onumbr2=(TextView)findViewById(R.id.onumb2);
        rooms1=(TextView)findViewById(R.id.rooms);
        beds1=(TextView)findViewById(R.id.beds);

        rooms1.setText("08");
        beds1.setText("22");

        PicassoClient.downloadImage(OwnerShowActivity.this,hostluri,image);
        name.setText(hostlname);

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("HostelInformation").orderByChild("id").equalTo(hostlid);
        //databaseReference.addValueEventListener(new ValueEventListener() {
        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showdata(dataSnapshot);
                Toast.makeText(OwnerShowActivity.this,"Stroing....",Toast.LENGTH_SHORT).show();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    hostelInfo=ds.getValue(HostelInfo.class);

                    address.setText(hostelInfo.getAddress());
                   // value.setText(hostelInfo.getValuee());
                    //security.setText(hostelInfo.getSecurity());
                    //location.setText(hostelInfo.getLocation());
                    //staff.setText(hostelInfo.getStaff());

                    //PicassoClient.downloadImage(Owner_PortalActivity.this,owner.getUri(),imageView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);

//        Query query2 = mFirebaseDatabaseReference.child("MapsInfo").orderByChild("id").equalTo(hostlid);
//        final ValueEventListener eventListener1=new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    map=ds.getValue(Map.class);
//                    latitude12=map.getLatitude();
//                    longitude13=map.getLongitude();
//                    nameMap=map.getName();
//                    PicassoClient.downloadImage(OwnerShowActivity.this,map.getUri(),imageView);
//                    //Toast.makeText(ShowdataActivity.this,"Value is love u " +longitude13,Toast.LENGTH_SHORT).show();
//                    ltitude=Double.toString(latitude12);
//                    logitude=Double.toString(longitude13);
//
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        };
//        query2.addValueEventListener(eventListener1);


//        Query query29 = mFirebaseDatabaseReference.child("Universities").orderByChild("name").equalTo(univrsty);
//        final ValueEventListener eventListener19=new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    map=ds.getValue(Map.class);
//                    m_lat=map.getLatitude();
//                    m_logi=map.getLongitude();
//                    lati_0=Double.toString(m_lat);
//                    longi_0=Double.toString(m_logi);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        };
//        query29.addValueEventListener(eventListener19);


//        Query query3 = mFirebaseDatabaseReference.child("Hostel_Property_Info").orderByChild("id").equalTo(hostlid);
//        final ValueEventListener eventListener2=new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    propertyInfo=ds.getValue(PropertyInfo.class);
//                    prop.setText(propertyInfo.getProperty());
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        };
//        query3.addValueEventListener(eventListener2);

//        Query query4 = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(ownerid);
//        final ValueEventListener eventListener3=new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    owner=ds.getValue(Owner.class);
//                    onames.setText(owner.getName());
//                    oemails.setText(owner.getEmail());
//                    onumbrs1.setText(owner.getNumber_1());
//                    onumbr2.setText(owner.getNumber_2());
//
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        };
//        query4.addValueEventListener(eventListener3);

//        ltitude=Double.toString(latitude);
//        logitude=Double.toString(longitude);
//        Toast.makeText(OwnerShowActivity.this,"Value is hate u " +logitude,Toast.LENGTH_SHORT).show();
//
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(OwnerShowActivity.this,MapsActivity.class);
//                intent.putExtra("id",hostlid);
//                intent.putExtra("name",nameMap);
//                intent.putExtra("lati",ltitude);
//                intent.putExtra("longi",logitude);
//                intent.putExtra("A_lati1",lati_0);
//                intent.putExtra("A_longi1",longi_0);
//                intent.putExtra("uni_name",univrsty);
//                startActivity(intent);
//
//            }
//        });

//        see.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                FragmentManager fm = getSupportFragmentManager();
//                Bundle bundle=new Bundle();
//                FacilitiesFragment fragment = new FacilitiesFragment();
//                bundle.putString("key",hostlid);
//                fragment.setArguments(bundle);
//                fm.beginTransaction().add(R.id.rel1,fragment).addToBackStack("null").commit();
//            }
//        });
    }



    }

