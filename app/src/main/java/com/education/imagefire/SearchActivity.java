package com.education.imagefire;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class SearchActivity extends AppCompatActivity {

    Button B1,B2,B3;
    EditText E1;
    private DatabaseReference databaseReference;
    private List<PropertyInfo> imglistt;
    private String hostelid;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId;
    private Spinner spin;
    private CheckBox check1;
    private CheckBox check2;
    String answer,gender;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView t1,t2;
    ImageView im1;
    Users users;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imglistt=new ArrayList<>();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigationB);
        toolbar = (Toolbar) findViewById(R.id.tool_bar2A);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.popupp);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawrB1);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        drawerLayout=(DrawerLayout)findViewById(R.id.drawr);
//        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView=(NavigationView)findViewById(R.id.nav_view);
        View navi=navigationView.inflateHeaderView(R.layout.nav_header_main);
        t1=(TextView) navi.findViewById(R.id.headtext);
        t2=(TextView)navi.findViewById(R.id.textView89);
        im1=(ImageView)navi.findViewById(R.id.profile_image);


        spin=(Spinner)findViewById(R.id.spinner);
        check1=(CheckBox)findViewById(R.id.male);
        check2=(CheckBox)findViewById(R.id.female);
        B1=(Button)findViewById(R.id.go);
        B3=(Button)findViewById(R.id.goes);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        Toast.makeText(SearchActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homes:
                        Toast.makeText(SearchActivity.this,"Home Already Open",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bells:
                        Toast.makeText(SearchActivity.this,"Functionality miss",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.likes:
                        Intent intent=new Intent(SearchActivity.this,MenuActivity.class);
                        startActivity(intent);

                        break;
                }
                return true;
            }
        });

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this, R.array.universities, android.R.layout.simple_spinner_item);
        spin.setAdapter(adapter);


        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("Users_Info").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Users.class);
                    t1.setText(users.getName());
                    t2.setText(users.getEmail());
                    PicassoClient.downloadImage(SearchActivity.this,users.getUri(),im1);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);

        listener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user != null){
                    // email=user.getEmail();
                    //ids=user.getUid();
                }else{
                    Toast.makeText(SearchActivity.this,"Sign out operation",Toast.LENGTH_SHORT).show();
                }
            }
        };


        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer=spin.getSelectedItem().toString();
                if(check1.isChecked()==true){
                    gender="male";
                }
                if(check2.isChecked()==true){
                    gender="female";
                }

                Intent intent = new Intent(SearchActivity.this, RecyclerviewActivity.class);
                    intent.putExtra("name",answer);
                    intent.putExtra("sex",gender);
                    startActivity(intent);

            }
        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(SearchActivity.this,"Signing out",Toast.LENGTH_SHORT).show();
                Intent intt=new Intent(SearchActivity.this,SigninActivity.class);
                startActivity(intt);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.userA1:
                Intent intent=new Intent(SearchActivity.this,ProfilessActivity.class);
                startActivity(intent);
            break;

            case R.id.userB2:
                firebaseAuth.signOut();
                Toast.makeText(SearchActivity.this,"Signing out",Toast.LENGTH_SHORT).show();
                Intent intt=new Intent(SearchActivity.this,SigninActivity.class);
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
