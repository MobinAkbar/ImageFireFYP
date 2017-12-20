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

import static android.R.attr.id;
import static android.R.attr.value;

public class PropertyActivity extends AppCompatActivity {

    private EditText property;
    private EditText nearby1;
    private EditText nearby2;
    private EditText nearby3;
    private EditText nearby4;
    private EditText nearby5;
    private EditText nearby6;
    private EditText nearby7;
    private EditText nearby8;
    private EditText nearby9;
    private EditText nearby10;
    Button upload,next;
    DatabaseReference databaseReference;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        property=(EditText)findViewById(R.id.place_info);
        nearby1=(EditText)findViewById(R.id.near_1);
        nearby2=(EditText)findViewById(R.id.near_2);
        nearby3=(EditText)findViewById(R.id.near_3);
        nearby4=(EditText)findViewById(R.id.near_4);
        nearby5=(EditText)findViewById(R.id.near_5);
        nearby6=(EditText)findViewById(R.id.near_6);
        nearby7=(EditText)findViewById(R.id.near_7);
        nearby8=(EditText)findViewById(R.id.near_8);
        nearby9=(EditText)findViewById(R.id.near_9);
        nearby10=(EditText)findViewById(R.id.near_10);

        upload=(Button)findViewById(R.id.upload4);
        next=(Button)findViewById(R.id.next4);

        key = getIntent().getStringExtra("id");
        databaseReference = FirebaseDatabase.getInstance().getReference("Hostel_Property_Info").child(key);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMember();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt=new Intent(PropertyActivity.this,NearbyplacesActivity.class);
                intnt.putExtra("id",key);
                startActivity(intnt);
            }
        });
    }
    private void addMember(){

           String surroundings=property.getText().toString();
           String places_1=nearby1.getText().toString();
        String places_2=nearby2.getText().toString();
        String places_3=nearby3.getText().toString();
        String places_4=nearby4.getText().toString();
        String places_5=nearby5.getText().toString();
        String places_6=nearby6.getText().toString();
        String places_7=nearby7.getText().toString();
        String places_8=nearby8.getText().toString();
        String places_9=nearby9.getText().toString();
        String places_10=nearby10.getText().toString();

            PropertyInfo infor=new PropertyInfo(key,surroundings,places_1,places_2,places_3,places_4,places_5,places_6,places_7,places_8,places_9,places_10);
            databaseReference.setValue(infor);

            Toast.makeText(this,"Successfulyy entered",Toast.LENGTH_LONG).show();


    }
}