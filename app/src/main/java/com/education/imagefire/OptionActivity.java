package com.education.imagefire;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class OptionActivity extends AppCompatActivity {


     Button button;
     CheckBox checkBox1,checkBox2;
   // private FirebaseAuth auth;
   // private FirebaseDatabase database;
   // private String UserId;
   // private FirebaseAuth.AuthStateListener stateListener;
   // private StorageReference storageReference;
   // private DatabaseReference databaseReference;
   // public static final String FB_DATABASE_PATH="Users_Type";
   // String email;
   // String password;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);


        button=(Button)findViewById(R.id.nexttt);
        checkBox1=(CheckBox)findViewById(R.id.owner);
        checkBox2=(CheckBox)findViewById(R.id.student);

                button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBox1.isChecked() && !checkBox2.isChecked()) {
                    Intent intent = new Intent(OptionActivity.this, MainActivity.class);
                    intent.putExtra("type", "owner");
                    startActivity(intent);
                    finish();
                } else if (checkBox2.isChecked() && !checkBox1.isChecked()) {
                    Intent intent = new Intent(OptionActivity.this, UserInfoActivity.class);
                    intent.putExtra("type", "student");
                    startActivity(intent);
                    finish();
                } else if (!checkBox1.isChecked() && !checkBox2.isChecked()) {
                    Toast.makeText(OptionActivity.this, "Enter Type", Toast.LENGTH_SHORT).show();
                } else if (checkBox1.isChecked() && checkBox2.isChecked()) {
                    Toast.makeText(OptionActivity.this, "Enter one Type", Toast.LENGTH_SHORT).show();
                }
            }
        });





//        email=getIntent().getStringExtra("email");
//        password=getIntent().getStringExtra("password");
//
//        auth=FirebaseAuth.getInstance();
//        database=FirebaseDatabase.getInstance();
//        databaseReference=database.getReference();
//        FirebaseUser user=auth.getCurrentUser();
//        UserId=user.getUid();
//
//        stateListener=new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user=firebaseAuth.getCurrentUser();
//                if(user != null){
//                    Toast.makeText(OptionActivity.this,"Id have something"+UserId,Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(OptionActivity.this,"Id is empty",Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        storageReference = FirebaseStorage.getInstance().getReference();
//        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH).child(UserId);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                upload();
//
//                if(checkBox1.isChecked()==true){
//                Intent intent=new Intent(OptionActivity.this, MainActivity.class);
//                intent.putExtra("email",email);
//                intent.putExtra("password",password);
//                startActivity(intent);
//                finish();}
//                else {
//                    Intent intent=new Intent(OptionActivity.this, UserInfoActivity.class);
//                    intent.putExtra("email",email);
//                    intent.putExtra("password",password);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        auth.addAuthStateListener(stateListener);
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (stateListener != null) {
//            auth.removeAuthStateListener(stateListener);
//        }
//    }
//    public void upload(){
//
//        if(checkBox1.isChecked()==true){
//            type="owner";
//
//        }
//        if(checkBox2.isChecked()==true){
//            type="student";
//        }
//
//        UserType userType=new UserType(UserId,type);
//        databaseReference.setValue(userType);
//

    }
}
