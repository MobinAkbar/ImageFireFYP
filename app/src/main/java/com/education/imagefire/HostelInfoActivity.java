package com.education.imagefire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.R.attr.id;
import static android.R.attr.key;
import static android.R.attr.value;

public class HostelInfoActivity extends AppCompatActivity {

    EditText E1,E2,E3,E4,E5;
    Button upload,next;
    DatabaseReference databaseReference;
    //StorageReference storageReference;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_info);

        E1 = (EditText) findViewById(R.id.e1);
        E2 = (EditText) findViewById(R.id.e2);
        E3 = (EditText) findViewById(R.id.e3);
        E4 = (EditText) findViewById(R.id.e5);
        E5 = (EditText) findViewById(R.id.e6);
        upload = (Button) findViewById(R.id.upload);
        next = (Button) findViewById(R.id.next);

         key = getIntent().getStringExtra("id");
       // storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("HostelInformation").child(key);
       // key = getIntent().getStringExtra("id");
       // databaseReference = FirebaseDatabase.getInstance().getReference("HostelInformation");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addMember();
                String adrs = E1.getText().toString().trim();
                String val = E2.getText().toString().trim();
                String scur = E3.getText().toString().trim();
                String sta = E4.getText().toString().trim();
                String loca = E5.getText().toString().trim();

                if (!TextUtils.isEmpty(adrs)) {

                    //String id=databaseReference.push().getKey();
                    //ID=id;
                    HostelInfo info = new HostelInfo(adrs, val, scur, sta, loca);
                    databaseReference.setValue(info);

                    Toast.makeText(HostelInfoActivity.this, "Successfulyy entered", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(HostelInfoActivity.this, "Must Enter Name", Toast.LENGTH_LONG).show();
                }


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt=new Intent(HostelInfoActivity.this,MapInfoActivity.class);
                intnt.putExtra("id",key);
                startActivity(intnt);
            }
        });
    }

//        private void addMember(){
//
//           String adres = address.getText().toString().trim();
//            String valu = value.getText().toString().trim();
//            String scurity = security.getText().toString().trim();
//            String cleanlines = cleanliness.getText().toString().trim();
//            String staf = staff.getText().toString().trim();
//            String locations = location.getText().toString().trim();
//
//            if(!TextUtils.isEmpty(adres)){
//
//                //String id=databaseReference.push().getKey();
//                //ID=id;
//                HostelInfo info=new HostelInfo(adres,valu,scurity,cleanlines,staf,locations);
//                databaseReference.setValue(info);
//
//                Toast.makeText(this,"Successfulyy entered",Toast.LENGTH_LONG).show();
//
//            } else{
//                Toast.makeText(this,"Must Enter Name",Toast.LENGTH_LONG).show();
//            }
//
//        }

    }
