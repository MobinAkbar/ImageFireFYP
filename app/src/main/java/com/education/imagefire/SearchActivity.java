package com.education.imagefire;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
    Dialog myDialog;
    private FloatingActionButton fab;
    private DatabaseReference databaseReference;
    private DatabaseReference cdatabaserefer;
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
        myDialog=new Dialog(this);

        imglistt=new ArrayList<>();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigationB);
        toolbar = (Toolbar) findViewById(R.id.tool_bar2A);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.popupp);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawrB1);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showComplainPopup();
            }
        });


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

             cdatabaserefer=FirebaseDatabase.getInstance().getReference("Complains");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.s_home:
                        Intent intent9=new Intent(SearchActivity.this,SearchActivity.class);
                        startActivity(intent9);
                        break;
                    case R.id.s_profile:
                        Intent intent=new Intent(SearchActivity.this,ProfilessActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.s_like:
                        Intent intent1=new Intent(SearchActivity.this,MenuActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.s_account:
                        Intent intent2=new Intent(SearchActivity.this,AccountActivity.class);
                        intent2.putExtra("type","student");
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
                        Toast.makeText(SearchActivity.this,"Signing out",Toast.LENGTH_SHORT).show();
                        Intent intt=new Intent(SearchActivity.this,SigninActivity.class);
                        startActivity(intt);
                        return true;
                }
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homes:
                        Toast.makeText(SearchActivity.this,"Home Already Open",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bells:
                        Intent intent1=new Intent(SearchActivity.this,NotificationActivity.class);
                        intent1.putExtra("type","student");
                        startActivity(intent1);
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

                if (check1.isChecked()&&check2.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Select one!", Toast.LENGTH_SHORT).show();
                    return;
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

    public void showComplainPopup(){
        Button button;
        final EditText name,hostel,compln;
        //final String ownr,hosname,detail;
        myDialog.setContentView(R.layout.complainpopup);

        button = (Button) myDialog.findViewById(R.id.s_comp);
        name=(EditText)myDialog.findViewById(R.id.name_co);
        hostel=(EditText)myDialog.findViewById(R.id.name_ho);
        compln=(EditText)myDialog.findViewById(R.id.comp);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id=cdatabaserefer.push().getKey();
                String s_id=UserId;
               String ownr=name.getText().toString();
               String name=hostel.getText().toString();
               String detail=compln.getText().toString();

                if (TextUtils.isEmpty(ownr)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(detail)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Complain complain=new Complain(id,s_id,ownr,name,detail);
                cdatabaserefer.child(id).setValue(complain);
                Toast.makeText(SearchActivity.this,"Complain Sent",Toast.LENGTH_SHORT).show();
                myDialog.dismiss();
            }
        });
        myDialog.show();
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}
