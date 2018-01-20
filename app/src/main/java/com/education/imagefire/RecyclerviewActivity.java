package com.education.imagefire;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    TextView t1,t2;
    ImageView im1;
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        myDialog=new Dialog(this);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigationB);
        toolbar = (Toolbar) findViewById(R.id.tool_bar2A);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.popupp);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawrB1);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView=(NavigationView)findViewById(R.id.nav_view);
        View navi=navigationView.inflateHeaderView(R.layout.nav_header_main);
        t1=(TextView) navi.findViewById(R.id.headtext);
        t2=(TextView)navi.findViewById(R.id.textView89);
        im1=(ImageView)navi.findViewById(R.id.profile_image);


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

        DatabaseReference mFirebaseDatabaseReference7 = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference7.child("Users_Info").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Users users=ds.getValue(Users.class);
                    t1.setText(users.getName());
                    t2.setText(users.getEmail());
                    PicassoClient.downloadImage(RecyclerviewActivity.this,users.getUri(),im1);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.s_home:
                        Toast.makeText(RecyclerviewActivity.this,"Already Opened",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.s_profile:
                        Intent intent=new Intent(RecyclerviewActivity.this,ProfilessActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.s_like:
                        Intent intent1=new Intent(RecyclerviewActivity.this,MenuActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.s_account:
                        Intent intent2=new Intent(RecyclerviewActivity.this,AccountActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.s_info:
                        final Button button;
                        myDialog.setContentView(R.layout.aboutuspopup);
                        button=(Button)myDialog.findViewById(R.id.cancel);
                        button.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.show();

                        break;
                    case R.id.s_help:
                        final Button button1;
                        myDialog.setContentView(R.layout.help_popup);
                        button1=(Button)myDialog.findViewById(R.id.cancel);
                        button1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.show();

                        break;
                    case R.id.s_signout:
                        firebaseAuth.signOut();
                        Toast.makeText(RecyclerviewActivity.this,"Signing out",Toast.LENGTH_SHORT).show();
                        Intent intt=new Intent(RecyclerviewActivity.this,SigninActivity.class);
                        startActivity(intt);
                        return true;
                }
                return true;
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.userA1:
                Intent intent=new Intent(RecyclerviewActivity.this,ProfilessActivity.class);
                startActivity(intent);
                break;

            case R.id.userB2:
                firebaseAuth.signOut();
                Toast.makeText(RecyclerviewActivity.this,"Signing out",Toast.LENGTH_SHORT).show();
                Intent intt=new Intent(RecyclerviewActivity.this,SigninActivity.class);
                startActivity(intt);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popupp, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

}

