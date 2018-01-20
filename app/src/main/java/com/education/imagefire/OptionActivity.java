package com.education.imagefire;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class OptionActivity extends AppCompatActivity {


     Button button;
     CheckBox checkBox1,checkBox2;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);


        button=(Button)findViewById(R.id.nexttt);
        checkBox1=(CheckBox)findViewById(R.id.owner);
        checkBox2=(CheckBox)findViewById(R.id.student);

                button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBox1.isChecked()&&!checkBox2.isChecked()) {
                    Intent intent = new Intent(OptionActivity.this, SignupActivity.class);
                    intent.putExtra("type", "owner");
                    startActivity(intent);
                    finish();
                } else if (checkBox2.isChecked()&&!checkBox1.isChecked()) {
                    Intent intent = new Intent(OptionActivity.this, SignupActivity.class);
                    intent.putExtra("type", "student");
                    startActivity(intent);
                    finish();
                } else if (!checkBox1.isChecked() && !checkBox2.isChecked()) {
                    Toast.makeText(OptionActivity.this, "Enter Type", Toast.LENGTH_SHORT).show();
                } else if (checkBox1.isChecked() && checkBox2.isChecked()) {
                    Toast.makeText(OptionActivity.this, "Enter one Type", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
