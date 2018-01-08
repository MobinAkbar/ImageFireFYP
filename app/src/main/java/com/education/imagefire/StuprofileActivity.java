package com.education.imagefire;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
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

public class StuprofileActivity extends AppCompatActivity {

    TextView name,type,email,adress,number,number2,number3,job;
    CircleImageView image;
    ImageButton imageButton;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    String UserId;
    Owner users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuprofile);

        imageButton=(ImageButton)findViewById(R.id.imagee100);
        image=(CircleImageView)findViewById(R.id.profile_image);
        name=(TextView)findViewById(R.id.name_o);
        email=(TextView)findViewById(R.id.mail_o);
        adress=(TextView)findViewById(R.id.adress_o);
        number=(TextView)findViewById(R.id.num1r_0);
        number2=(TextView)findViewById(R.id.num2r_o);
        number3=(TextView)findViewById(R.id.num3r_o);
        job=(TextView)findViewById(R.id.job_o);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        Toast.makeText(StuprofileActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Owner.class);

                    name.setText(users.getName());
                    job.setText(users.getProfessionn());
                    number.setText(users.getNumber_1());
                    number2.setText(users.getNumber_2());
                    number3.setText(users.getNumber_3());
                    email.setText(users.getEmail());
                    PicassoClient.downloadImage(StuprofileActivity.this,users.getUri(),image);

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
                PopupMenu popup = new PopupMenu(StuprofileActivity.this, imageButton);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                                Intent intent = new Intent(StuprofileActivity.this, OwnerProfileActivity.class);
                                startActivity(intent);
                                return true;
                        }
                });
                popup.show();
            }
        });
    }
}