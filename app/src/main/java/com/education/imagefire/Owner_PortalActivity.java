package com.education.imagefire;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.key;
import static android.R.attr.value;
import static com.education.imagefire.R.id.recyclerView;

public class Owner_PortalActivity extends AppCompatActivity {

    private TextView T1;
    private TextView T2;
    private TextView signout;
    private TextView T4;
    Button B1,B2;
    private ImageView imageView;
    List<Hostel> listhos;


    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
   // private RecyclerView recyclerView;
    private ListView listView;
    private String UserId;
   // private List<RecyclerUpload> list;
    private Owner owner;
    private Hostel hostel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner__portal);

       // list.clear();
        B1=(Button)findViewById(R.id.newhostel);
        //B2=(Button)findViewById(R.id.signout);
        imageView=(ImageView)findViewById(R.id.imageView);
        T1=(TextView)findViewById(R.id.name);
        T2=(TextView)findViewById(R.id.numb1);
        signout=(TextView)findViewById(R.id.out);
        T4=(TextView)findViewById(R.id.email);
       // recyclerView=(RecyclerView)findViewById(R.id.recycle);
           listView=(ListView)findViewById(R.id.recyclerView);
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

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(UserId);

       // databaseReference.addValueEventListener(new ValueEventListener() {
        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showdata(dataSnapshot);
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    owner=ds.getValue(Owner.class);

                    //Owner owner=new Owner();
                    //owner.setName(ds.child(UserId).getValue(Owner.class).getName());
                    //owner.setNumber_1(ds.child(UserId).getValue(Owner.class).getNumber_1());
                    //owner.setUri(ds.child(UserId).getValue(Owner.class).getUri());

                    T1.setText(owner.getName());
                    T2.setText(owner.getNumber_1());
                    //T3.setText(owner.getNumber_2());
                    T4.setText(owner.getEmail());
                    // imageView.setImageURI(owner.getUri());
                    //Picasso.with(Owner_PortalActivity.this).load(owner.getUri()).resize(100, 100).into(imageView);
                    // PicassoClient.downloadImage(Owner_PortalActivity.this,owner.getUri(),imageView);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        query.addValueEventListener(eventListener);

        Toast.makeText(Owner_PortalActivity.this,"IM here running",Toast.LENGTH_SHORT).show();



        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                    Intent intent = new Intent(Owner_PortalActivity.this, HostelActivity.class);
                    intent.putExtra("UID", UserId);
                    startActivity(intent);
                }
            }
        );

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(Owner_PortalActivity.this,"Digning out",Toast.LENGTH_SHORT).show();
                Intent intt=new Intent(Owner_PortalActivity.this,OwnerSignInActivity.class);
                startActivity(intt);
            }
        });
}



//    private void showhostels(DataSnapshot dataSnapshot) {
//        for(DataSnapshot dn:dataSnapshot.getChildren()){
//            Hostel hostel=dn.child(UserId).getValue(Hostel.class);
//
//            //String name=owner.getName();
//            //String uri=owner.getUri();
//            //list.clear();
//            list.add(hostel);
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(listener);

        databaseReference=FirebaseDatabase.getInstance().getReference("Hostels").child(UserId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listhos.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Hostel hostel=ds.getValue(Hostel.class);
                    listhos.add(hostel);

                }
                CarAdapter carAdapter=new CarAdapter(Owner_PortalActivity.this,listhos);
                listView.setAdapter(carAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            firebaseAuth.removeAuthStateListener(listener);
        }
    }
//    private void showdata(DataSnapshot dataSnapshot) {
//        for(DataSnapshot ds:dataSnapshot.getChildren()){
//            //Owner owner=new Owner();
//            owner.setName(ds.child(UserId).getValue(Owner.class).getName());
//            owner.setNumber_1(ds.child(UserId).getValue(Owner.class).getNumber_1());
//            owner.setNumber_2(ds.child(UserId).getValue(Owner.class).getNumber_2());
//            owner.setEmail(ds.child(UserId).getValue(Owner.class).getEmail());
//            owner.setUri(ds.child(UserId).getValue(Owner.class).getUri());
//
//            T1.setText(owner.getName());
//            T2.setText(owner.getNumber_1());
//            T3.setText(owner.getNumber_2());
//            T4.setText(owner.getEmail());
//           // imageView.setImageURI(owner.getUri());
//            //Picasso.with(Owner_PortalActivity.this).load(owner.getUri()).resize(100, 100).into(imageView);
//           // PicassoClient.downloadImage(Owner_PortalActivity.this,owner.getUri(),imageView);
//
//        }
//    }
}
