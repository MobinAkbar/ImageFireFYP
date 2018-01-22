package com.education.imagefire;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.R.attr.key;
import static android.R.attr.value;
import static android.R.id.toggle;
import static com.education.imagefire.R.id.recyclerView;
import static com.education.imagefire.R.id.start;

public class Owner_PortalActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    private ArrayList<RecyclerUpload2> hostelList;
    private TextView T1;
    private TextView T2;
    private TextView signout;
    private TextView T4;
    Button B1,B2,B3;
    private ImageView imageView;
    List<Hostel> listhos;
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
    private String UserId;
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
    BottomNavigationView bottomnavigationView;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner__portal);

        myDialog=new Dialog(this);
        toolbar = (Toolbar) findViewById(R.id.tool_bar1A);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.pop_up);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawrB2);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);

        // toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hostelList = new ArrayList<>();
        recyclerview = (RecyclerView) findViewById(R.id.recycle);
        recyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        View navi=navigationView.inflateHeaderView(R.layout.nav_header_main);
        t1=(TextView) navi.findViewById(R.id.headtext);
        t2=(TextView)navi.findViewById(R.id.textView89);
        im1=(ImageView)navi.findViewById(R.id.profile_image);
        bottomnavigationView=(BottomNavigationView)findViewById(R.id.navigationB);
       // list.clear();


        listhos=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        Toast.makeText(Owner_PortalActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();


        listener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user != null){
                    // email=user.getEmail();
                    //ids=user.getUid();
                }else{
                    Toast.makeText(Owner_PortalActivity.this,"Sign out operation",Toast.LENGTH_SHORT).show();
                }
            }
        };

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.o_home:
                        Intent intent8=new Intent(Owner_PortalActivity.this,Owner_PortalActivity.class);
                        startActivity(intent8);
                        break;
                    case R.id.o_profile:
                        Intent intent=new Intent(Owner_PortalActivity.this,StuprofileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.o_reset:
                        Intent intent1=new Intent(Owner_PortalActivity.this,AccountActivity.class);
                        intent1.putExtra("type","owner");
                        startActivity(intent1);
                        break;
                    case R.id.o_info:
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
                    case R.id.o_help:
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
                    case R.id.o_signout:
                        firebaseAuth.signOut();
                        Toast.makeText(Owner_PortalActivity.this,"Signing out",Toast.LENGTH_SHORT).show();
                        Intent intt=new Intent(Owner_PortalActivity.this,SigninActivity.class);
                        startActivity(intt);
                        return true;
                }
                return true;
            }
        });

        bottomnavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_homeo:
                        Intent intent=new Intent(Owner_PortalActivity.this,Owner_PortalActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.likeso:
                        Intent intent1=new Intent(Owner_PortalActivity.this,StuprofileActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.menu_notfyo:
                        Intent intent2=new Intent(Owner_PortalActivity.this,NotificationActivity.class);
                        intent2.putExtra("type","owner");
                        startActivity(intent2);

                        break;
                }
                return true;
            }
        });

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Owner.class);
                    t1.setText(users.getName());
                    t2.setText(users.getEmail());
                    PicassoClient.downloadImage(Owner_PortalActivity.this,users.getUri(),im1);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);
}
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(listener);
        hostelList.clear();
       // databaseReference=FirebaseDatabase.getInstance().getReference("Hostels").orderByChild("owner").equalTo(UserId);
        //databaseReference.addValueEventListener(new ValueEventListener() {
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseDatabaseReference.child("Hostels").orderByChild("owner").equalTo(UserId);
        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Hostel hostel = ds.getValue(Hostel.class);

                    if (hostel.getStatus().equals("APPROVED")) {
                        String hostelId = hostel.getId();
                        String name = hostel.getName();
                        String adres = hostel.getAddres();
                        int like = hostel.getLikes();
                        String uri = hostel.getUri();
                        String type = hostel.getSex();
                        RecyclerUpload2 obj = new RecyclerUpload2(hostelId, name, adres, uri, like, type);
                        hostelList.add(obj);
                    }
                }

                Rcycleadpater recycler = new Rcycleadpater(Owner_PortalActivity.this,hostelList);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(Owner_PortalActivity.this);
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator( new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        query.addValueEventListener(eventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            firebaseAuth.removeAuthStateListener(listener);
        }
    }
    public void restsrt(){
        //hostelList.clear();
        finish();
        startActivity(getIntent());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add3:
                Intent intent = new Intent(Owner_PortalActivity.this, HostelActivity.class);
                intent.putExtra("UID", UserId);
                startActivity(intent);
                break;

            case R.id.userr1:
                Intent intent1=new Intent(Owner_PortalActivity.this,StuprofileActivity.class);
                startActivity(intent1);
                break;

            case R.id.userr2:
                firebaseAuth.signOut();
                Toast.makeText(Owner_PortalActivity.this,"Signing out",Toast.LENGTH_SHORT).show();
                Intent intt=new Intent(Owner_PortalActivity.this,SigninActivity.class);
                startActivity(intt);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pop_up, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
