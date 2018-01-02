package com.education.imagefire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StuprofileActivity extends AppCompatActivity {

    TextView name,type,email,adress,number,number2,number3,job;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuprofile);


        name=(TextView)findViewById(R.id.name_o);
        type=(TextView)findViewById(R.id.type_o);
        email=(TextView)findViewById(R.id.mail_o);
        adress=(TextView)findViewById(R.id.adress_o);
        number=(TextView)findViewById(R.id.num1r_0);
        number2=(TextView)findViewById(R.id.num2r_o);
        number3=(TextView)findViewById(R.id.num3r_o);
        job=(TextView)findViewById(R.id.job_o);





    }
}
