package com.education.imagefire;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class OwnerSignInActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private FirebaseAuth.AuthStateListener stateListener;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intt=new Intent(OwnerSignInActivity.this,BasicsActivity.class);
        startActivity(intt);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        stateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user != null){
                   // email=user.getEmail();
                    //ids=user.getUid();
                }else{
                    Toast.makeText(OwnerSignInActivity.this,"Sign out operation",Toast.LENGTH_SHORT).show();
                }
            }
        };

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(OwnerSignInActivity.this, Owner_PortalActivity.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_signin);


        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SigninActivity.this, SignupActivity.class));
//            }
//        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerSignInActivity.this, OwnerSignUpActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(OwnerSignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(OwnerSignInActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(OwnerSignInActivity.this, Owner_PortalActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(stateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (stateListener != null) {
            auth.removeAuthStateListener(stateListener);
        }

    }

//    private EditText inputEmail, inputPassword;
//    private StorageReference storageReference;
//   // private ProgressBar progressBar;
//    private Button btnLogin, btnReset;
//    private List<Owner> imglistt;
//   Owner owner1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_owner_sign_in);
//
//        owner1=new Owner();
//        imglistt=new ArrayList<>() ;
//        inputEmail = (EditText) findViewById(R.id.email);
//        inputPassword = (EditText) findViewById(R.id.password);
//       // progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        btnLogin = (Button) findViewById(R.id.btn_login);
//        btnReset = (Button) findViewById(R.id.btn_reset_password);
//
//
//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(OwnerSignInActivity.this, MainActivity.class));
//            }
//        });
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = inputEmail.getText().toString();
//                String password = inputPassword.getText().toString();
//                //imglistt=null;
//
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//               // progressBar.setVisibility(View.VISIBLE);
//
//                //authenticate user
////                auth.signInWithEmailAndPassword(email, password)
////                        .addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
////                            @Override
////                            public void onComplete(@NonNull Task<AuthResult> task) {
////                                // If sign in fails, display a message to the user. If sign in succeeds
////                                // the auth state listener will be notified and logic to handle the
////                                // signed in user can be handled in the listener.
////                                progressBar.setVisibility(View.GONE);
////                                if (!task.isSuccessful()) {
////                                    // there was an error
////                                    if (password.length() < 6) {
////                                        inputPassword.setError(getString(R.string.minimum_password));
////                                    } else {
////                                        Toast.makeText(OwnerSignInActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
////                                    }
////                                } else {
////                                    Intent intent = new Intent(OwnerSignInActivity.this, Owner_PortalActivity.class);
////                                    startActivity(intent);
////                                    finish();
////                                }
////                            }
////                        });
//
//                //String value=;
//                //Toast.makeText(ResultActivity.this, "i have" +value , Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
//        Query query = mFirebaseDatabaseReference.child("Owners").orderByChild("decsription").equalTo(password);
//        Toast.makeText(OwnerSignInActivity.this,"running here ",Toast.LENGTH_SHORT).show();
//
//        ValueEventListener valueEventListener = new ValueEventListener()
//        {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                Toast.makeText(OwnerSignInActivity.this,"running here 2",Toast.LENGTH_SHORT).show();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
//                {
//                    Owner owner=postSnapshot.getValue(Owner.class);
//                    //Owner per=postSnapshot.getValue(Owner.class);
//                    owner1=owner;
//                    //imglistt.add(1,postSnapshot.getValue(Owner.class));
//                    //TODO get the data here
//                    //String name=owner1.getName();
//                    Toast.makeText(OwnerSignInActivity.this,"running ",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError)
//            {
//
//            }
//        };
//        query.addValueEventListener(valueEventListener);
//
//        if(owner1!=null){
//
//            Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Intent in=new Intent(OwnerSignInActivity.this,Owner_PortalActivity.class);
//            startActivity(in);
//
//        }
//
//    }
}
