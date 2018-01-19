package com.education.imagefire;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountActivity extends AppCompatActivity {

    Button button;
    EditText past,future;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        button=(Button)findViewById(R.id.passsave);
        past=(EditText)findViewById(R.id.previous);
        future=(EditText)findViewById(R.id.newpass);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        email=user.getEmail();

        final String newpass=past.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AccountActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });











    }
}
