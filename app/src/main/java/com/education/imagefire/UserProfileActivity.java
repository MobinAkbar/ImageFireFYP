package com.education.imagefire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.util.List;

import static com.education.imagefire.R.id.address;
//import static com.education.imagefire.R.id.imageView;
import static com.education.imagefire.R.id.location;
import static com.education.imagefire.R.id.name20;
import static com.education.imagefire.R.id.security;

public class UserProfileActivity extends AppCompatActivity {


    TextView name,adres,numb,university,email,password;
    CheckBox check1,check2;
    Button B1;
    ImageView image1;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    String UserId;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name=(TextView)findViewById(R.id.name20);
        adres=(TextView)findViewById(R.id.address20);
        numb=(TextView)findViewById(R.id.numb20);
        university=(TextView)findViewById(R.id.univ20);
        email=(TextView)findViewById(R.id.mail20);
        password=(TextView)findViewById(R.id.password20);
        B1=(Button)findViewById(R.id.editchange20);
        image1=(ImageView)findViewById(R.id.image20);
        check1=(CheckBox)findViewById(R.id.male20);
        check2=(CheckBox)findViewById(R.id.female20);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        Toast.makeText(UserProfileActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("Users_Info").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Users.class);

                    name.setText(users.getName());
                    adres.setText(users.getAdress());
                    numb.setText(users.getNumber());
                    university.setText(users.getUniversity());
                    email.setText(users.getEmail());
                    password.setText(users.getPassword());
                    PicassoClient.downloadImage(UserProfileActivity.this,users.getUri(),image1);
                    if(users.getSex()=="male"){
                        check1.setPressed(true);
                    }else{
                        check2.setPressed(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);
    }
}
