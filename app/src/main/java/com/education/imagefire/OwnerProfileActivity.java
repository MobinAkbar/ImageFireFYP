package com.education.imagefire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
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

import org.w3c.dom.Text;

public class OwnerProfileActivity extends AppCompatActivity {

    TextView name,profession,numb1,numb2,numb3,email,password;
    Button B1;
    ImageView image;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    String UserId;
    Owner users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);


//        name=(TextView)findViewById(R.id.name19);
//        profession=(TextView)findViewById(R.id.profesion19);
//        numb1=(TextView)findViewById(R.id.mobile_a19);
//        numb2=(TextView)findViewById(R.id.mobile_b19);
//        numb3=(TextView)findViewById(R.id.mobile_c19);
//        email=(TextView)findViewById(R.id.email19);
//        password=(TextView)findViewById(R.id.password19);
//        B1=(Button)findViewById(R.id.editchange19);
//        image=(ImageView)findViewById(R.id.image19);
//
//        firebaseAuth=FirebaseAuth.getInstance();
//        firebaseDatabase=FirebaseDatabase.getInstance();
//        databaseReference=firebaseDatabase.getReference();
//        final FirebaseUser user=firebaseAuth.getCurrentUser();
//        UserId=user.getUid();
//        Toast.makeText(OwnerProfileActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();
//
//        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
//        Query query1 = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(UserId);
//
//        final ValueEventListener eventListener=new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    users=ds.getValue(Owner.class);
//
//                    name.setText(users.getName());
//                    profession.setText(users.getProfessionn());
//                    numb1.setText(users.getNumber_1());
//                    numb2.setText(users.getNumber_2());
//                    numb3.setText(users.getNumber_3());
//                    email.setText(users.getEmail());
//                    password.setText(users.getPassword());
//                    PicassoClient.downloadImage(OwnerProfileActivity.this,users.getUri(),image);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        query1.addValueEventListener(eventListener);
//
//
//
//
//
//
//


    }
}
