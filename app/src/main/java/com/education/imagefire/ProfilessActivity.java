package com.education.imagefire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ProfilessActivity extends AppCompatActivity {

    TextView name,type,email,adress,number,university,gender;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiless);

        name=(TextView)findViewById(R.id.name_o);
        type=(TextView)findViewById(R.id.type_o);
        email=(TextView)findViewById(R.id.mail_o);
        adress=(TextView)findViewById(R.id.adress_o);
        number=(TextView)findViewById(R.id.numb_o);
        university=(TextView)findViewById(R.id.universty_o);
        gender=(TextView)findViewById(R.id.gendr_o);


    }
}