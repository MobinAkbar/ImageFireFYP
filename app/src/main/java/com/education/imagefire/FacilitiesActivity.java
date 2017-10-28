package com.education.imagefire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FacilitiesActivity extends AppCompatActivity {

    EditText wifi,air_conditioning,breakfast,parking,reception,electrition,gym,kitchen;
    Button upload,done;
    DatabaseReference databaseReference;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);

        wifi=(EditText)findViewById(R.id.e1);
        air_conditioning=(EditText)findViewById(R.id.e2);
        breakfast=(EditText)findViewById(R.id.e3);
        parking=(EditText)findViewById(R.id.e4);
        reception=(EditText)findViewById(R.id.e5);
        electrition=(EditText)findViewById(R.id.e6);
        gym=(EditText)findViewById(R.id.e7);
        kitchen=(EditText)findViewById(R.id.e8);
        upload=(Button)findViewById(R.id.upload);
        done=(Button)findViewById(R.id.done);
        key = getIntent().getStringExtra("id");
        databaseReference = FirebaseDatabase.getInstance().getReference("Hostel_Property_Info").child(key);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt=new Intent(FacilitiesActivity.this,Owner_PortalActivity.class);
                intnt.putExtra("id",key);
                startActivity(intnt);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMember();
            }
        });
    }
    private void addMember(){

        String frewifi = wifi.getText().toString().trim();
        String ac = air_conditioning.getText().toString().trim();
        String brkfast = breakfast.getText().toString().trim();
        String stand = parking.getText().toString().trim();
        String recption = reception.getText().toString().trim();
        String jym = gym.getText().toString().trim();
        String electric = electrition.getText().toString().trim();
        String kitchn = kitchen.getText().toString().trim();

        if(!TextUtils.isEmpty(frewifi)){

            //String id=databaseReference.push().getKey();
            //ID=id;
           Facilities fac=new Facilities(frewifi,ac,brkfast,stand,recption,jym,electric,kitchn);
            databaseReference.setValue(fac);

            Toast.makeText(this,"Successfulyy entered",Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this,"Must Enter Name",Toast.LENGTH_LONG).show();
        }

    }
}
