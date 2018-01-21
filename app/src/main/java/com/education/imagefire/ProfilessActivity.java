package com.education.imagefire;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilessActivity extends AppCompatActivity {

    TextView name,type,email,adress,number,university,gender;
    Button button;
    CircleImageView imageView;
    ImageButton imageButton;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    String UserId;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiless);

        name=(TextView)findViewById(R.id.name_o);
        type=(TextView)findViewById(R.id.type_o);
        email=(TextView)findViewById(R.id.mail_o);
        adress=(TextView)findViewById(R.id.adress_o);
        number=(TextView)findViewById(R.id.num1r_0);
        university=(TextView)findViewById(R.id.universty_o);
        gender=(TextView)findViewById(R.id.sex_o);
        imageView=(CircleImageView)findViewById(R.id.profile);
        imageButton=(ImageButton)findViewById(R.id.imagee200);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("Users_Info").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Users.class);


                    PicassoClient.downloadImage(ProfilessActivity.this,users.getUri(),imageView);
                    name.setText(users.getName());
                    email.setText(users.getEmail());
                    adress.setText(users.getAdress());
                    number.setText(users.getNumber());
                    university.setText(users.getUniversity());
                    gender.setText(users.getSex());

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(ProfilessActivity.this, imageButton);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(ProfilessActivity.this, UserProfileActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(ProfilessActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}
