package com.education.imagefire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity {

    Button B1;
    EditText E1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        B1=(Button)findViewById(R.id.go);
        E1=(EditText)findViewById(R.id.eee1);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer=E1.getText().toString();
                Intent o1=new Intent(SearchActivity.this,ResultActivity.class);
                o1.putExtra("name",answer);
                startActivity(o1);
            }
        });
    }
}
