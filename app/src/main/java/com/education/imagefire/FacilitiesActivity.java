package com.education.imagefire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.key;

public class FacilitiesActivity extends AppCompatActivity {

    String key;
    Button upload,next;
    DatabaseReference databaseReference;
    private CheckBox wifi2;
    private CheckBox kitchen2;
    private CheckBox generetor2;
    private CheckBox tuck2;
    private CheckBox camera2;
    private CheckBox elctrtion2;
    private CheckBox washer2;
    private CheckBox guest2;
    private CheckBox brekfast2;
    private CheckBox parking2;
    String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);

        upload=(Button)findViewById(R.id.upload2);
       // next=(Button)findViewById(R.id.next2);
        wifi2=(CheckBox)findViewById(R.id.wifi1);
        generetor2=(CheckBox)findViewById(R.id.generater1);
        tuck2=(CheckBox)findViewById(R.id.tuckshop1);
        parking2=(CheckBox)findViewById(R.id.parking1);
        elctrtion2=(CheckBox)findViewById(R.id.elctrition1);
        washer2=(CheckBox)findViewById(R.id.washrom1);
        brekfast2=(CheckBox)findViewById(R.id.breakfast1);
        guest2=(CheckBox)findViewById(R.id.guest1);
        camera2=(CheckBox)findViewById(R.id.camer1);
        kitchen2=(CheckBox)findViewById(R.id.kitchn1);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMember();
            }
        });

        key = getIntent().getStringExtra("id");
        databaseReference = FirebaseDatabase.getInstance().getReference("Facilities").child(key);

    }
    private void addMember(){

        String id=key;
        if(wifi2.isChecked()) {
            c1 = "Yes";
        }else{
            c1="No";
        }


        if(camera2.isChecked()) {
            c2 = "Yes";
        }else{
            c2="No";
        }

        if(parking2.isChecked()) {
            c3 = "Yes";;
        }
        else{
            c3="No";
        }

        if(brekfast2.isChecked()) {
            c4 = "Yes";;
        }else{
            c4="No";
        }

        if(elctrtion2.isChecked()) {
            c5 = "Yes";;
        }else{
            c5="No";
        }


        if(tuck2.isChecked()) {
            c6 = "Yes";;
        }else{
            c6="No";
        }

        if(guest2.isChecked()) {
            c7 = "Yes";;
        }else{
            c7="No";
        }

        if(generetor2.isChecked()) {
            c8 = "Yes";;
        }else{
            c8="No";
        }

        if(kitchen2.isChecked()) {
            c9 = "Yes";;
        }else{
            c9="No";
        }

        if(washer2.isChecked()) {
            c10 = "Yes";
        }else{
            c10="No";
        }

           Facilities fac=new Facilities(id,c1,c8,c4,c2,c5,c7,c6,c3,c10,c9);
            databaseReference.setValue(fac).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Intent intnt=new Intent(FacilitiesActivity.this,RoomsActivity.class);
                    intnt.putExtra("id",key);
                    startActivity(intnt);
                }
            });

            Toast.makeText(this,"Successfulyy entered",Toast.LENGTH_LONG).show();

    }
}
