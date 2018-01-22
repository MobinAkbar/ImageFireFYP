package com.education.imagefire;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    private ArrayList<Notification> hostelList;
    private TextView T1;
    private TextView T2;
    private TextView signout;
    private TextView T4;
    Button B1,B2,B3;
    private ImageView imageView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    // private RecyclerView recyclerView;
    private ListView listView;
    private String ownerId;
    private String UserId,form;
    private String HostelId;
    private String HostelId2;
    private String HostelName;
    private String HostelUri;
    // private List<RecyclerUpload> list;
    private Owner owner;
    private Hostel hostel;
    TextView t1,t2;
    ImageView im1;
    Owner users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        form=getIntent().getStringExtra("type");
        Toast.makeText(NotificationActivity.this,"having"+form,Toast.LENGTH_SHORT).show();

        hostelList = new ArrayList<>();
        recyclerview = (RecyclerView) findViewById(R.id.recycle);
        recyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        listener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user != null){
                    // email=user.getEmail();
                    //ids=user.getUid();
                }else{
                    Toast.makeText(NotificationActivity.this,"Sign out operation",Toast.LENGTH_SHORT).show();
                }
            }
        };

        hostelList.clear();

       if(form.equals("owner")) {
            DatabaseReference mFirebaseDatabaseReference1 = FirebaseDatabase.getInstance().getReference();
            Query query = mFirebaseDatabaseReference1.child("Notifications").orderByChild("sendto").equalTo(UserId);
            final ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Notification notification = ds.getValue(Notification.class);
                        hostelList.add(notification);
                    }

                    NotifyAdapter recycler = new NotifyAdapter(NotificationActivity.this, hostelList);
                    RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(NotificationActivity.this);
                    recyclerview.setLayoutManager(layoutmanager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(recycler);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            query.addValueEventListener(eventListener);
        }

        if(form.equals("student")){
            DatabaseReference mFirebaseDatabaseReference2 = FirebaseDatabase.getInstance().getReference();
            Query query1 = mFirebaseDatabaseReference2.child("Notifications").orderByChild("from").equalTo(UserId);
            final ValueEventListener eventListener1 = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Notification notification = ds.getValue(Notification.class);
                        hostelList.add(notification);
                    }

                    Notify1Adapter recycler = new Notify1Adapter(NotificationActivity.this, hostelList);
                    RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(NotificationActivity.this);
                    recyclerview.setLayoutManager(layoutmanager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(recycler);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            query1.addValueEventListener(eventListener1);

        }
    }

    public void restsrt(){
        //hostelList.clear();
        finish();
        startActivity(getIntent());
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(listener);

    }
    @Override
    public void onBackPressed() {
        form=getIntent().getStringExtra("type");
        if(form.equals("owner")){
            finish();
            Intent intent = new Intent(NotificationActivity.this, Owner_PortalActivity.class);
            startActivity(intent);

        }else{
            finish();
            Intent intent = new Intent(NotificationActivity.this, SearchActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            firebaseAuth.removeAuthStateListener(listener);
        }
    }
}
