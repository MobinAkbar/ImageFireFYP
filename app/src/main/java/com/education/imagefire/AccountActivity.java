package com.education.imagefire;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountActivity extends AppCompatActivity {

    Button b_email,b_password;
    EditText mail,pas,new_mail,new_pass;
    private DatabaseReference databaseReference,Databasereference1;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId,email,password;
    String typee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        typee=getIntent().getStringExtra("type");

        b_email=(Button)findViewById(R.id.email_save);
        b_password=(Button)findViewById(R.id.pass_save);

        mail=(EditText)findViewById(R.id.pre_email);
        pas=(EditText)findViewById(R.id.pre_pass);
        new_pass=(EditText)findViewById(R.id.new_passw);
        new_mail=(EditText)findViewById(R.id.new_email);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();

        b_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curr_email = mail.getText().toString();
                String curr_pass = pas.getText().toString();
                final String newpass = new_pass.getText().toString();
                Toast.makeText(AccountActivity.this,"Wait updating",Toast.LENGTH_SHORT).show();
                AuthCredential credential = EmailAuthProvider
                        .getCredential(curr_email, curr_pass);
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                               if(typee.equals("owner")){
                                                   databaseReference=FirebaseDatabase.getInstance().getReference("Owners").child(UserId);
                                                   databaseReference.child("password").setValue(newpass);
                                               }else{
                                                   databaseReference=FirebaseDatabase.getInstance().getReference("Users_Info").child(UserId);
                                                   databaseReference.child("password").setValue(newpass);
                                               }


                                                Toast.makeText(AccountActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(AccountActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(AccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                  }
            });

        b_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curr_email = mail.getText().toString();
                String curr_pass = pas.getText().toString();
                final String newmail = new_mail.getText().toString();
              Toast.makeText(AccountActivity.this,"Wait updating",Toast.LENGTH_SHORT).show();
                AuthCredential credential = EmailAuthProvider
                        .getCredential(curr_email, curr_pass);
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updateEmail(newmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(AccountActivity.this, "Successfull update email", Toast.LENGTH_SHORT).show();
                                                if(typee.equals("owner")){
                                                    databaseReference=FirebaseDatabase.getInstance().getReference("Owners").child(UserId);
                                                    databaseReference.child("password").setValue(newmail);
                                                }else{
                                                    databaseReference=FirebaseDatabase.getInstance().getReference("Users_Info").child(UserId);
                                                    databaseReference.child("password").setValue(newmail);
                                                }

                                            } else {
                                                Toast.makeText(AccountActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(AccountActivity.this, "Ejorror", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });
    }
}
