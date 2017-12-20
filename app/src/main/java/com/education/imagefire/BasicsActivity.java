package com.education.imagefire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.education.imagefire.R.id.stu;

public class BasicsActivity extends AppCompatActivity {


    Button student,owner,admin;
    Long back_pressed;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //System.exit(0);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);

        student=(Button)findViewById(R.id.btn_login_student);
        owner=(Button)findViewById(R.id.btn_login_owner);
        //admin=(Button)findViewById(R.id.btn_admin_use);


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt=new Intent(BasicsActivity.this,SigninActivity.class);
                startActivity(intt);

            }
        });

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt=new Intent(BasicsActivity.this,OwnerSignInActivity.class);
                startActivity(intt);

            }
        });

//        admin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intt=new Intent(BasicsActivity.this,UniversityActivity.class);
//                startActivity(intt);
//
//            }
//        });


    }
}