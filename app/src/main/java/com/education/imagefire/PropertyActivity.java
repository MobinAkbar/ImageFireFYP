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

import static android.R.attr.value;

public class PropertyActivity extends AppCompatActivity {

    EditText location_info,uni_1,uni_2,uni_3,location_1,location_2,location_3;
    Button upload,next;
    DatabaseReference databaseReference;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        location_info=(EditText)findViewById(R.id.e1);
        uni_1=(EditText)findViewById(R.id.e2);
        uni_2=(EditText)findViewById(R.id.e3);
        uni_3=(EditText)findViewById(R.id.e4);
        location_1=(EditText)findViewById(R.id.e5);
        location_2=(EditText)findViewById(R.id.e6);
        location_3=(EditText)findViewById(R.id.e7);
        upload=(Button)findViewById(R.id.upload);
        next=(Button)findViewById(R.id.next);

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
                Intent intnt=new Intent(PropertyActivity.this,FacilitiesActivity.class);
                intnt.putExtra("id",key);
                startActivity(intnt);
            }
        });
    }
    private void addMember(){

        String pro_info = location_info.getText().toString().trim();
        String univ1=uni_1.getText().toString().trim();
        String univ2=uni_2.getText().toString().trim();
        String univ3=uni_3.getText().toString().trim();
        String loc1=location_1.getText().toString().trim();
        String loc2=location_2.getText().toString().trim();
        String loc3=location_3.getText().toString().trim();

        if(!TextUtils.isEmpty(pro_info)){

            //String id=databaseReference.push().getKey();
            //ID=id;
            PropertyInfo infor=new PropertyInfo(pro_info,univ1,univ2,univ3,loc1,loc2,loc3);
            databaseReference.setValue(infor);

            Toast.makeText(this,"Successfulyy entered",Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this,"Must Enter Name",Toast.LENGTH_LONG).show();
        }

    }
}
