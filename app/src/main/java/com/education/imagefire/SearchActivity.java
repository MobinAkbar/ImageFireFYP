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

    Button B1,B2,B3;
    EditText E1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        B1=(Button)findViewById(R.id.go);
        B2=(Button)findViewById(R.id.go0);
        B3=(Button)findViewById(R.id.go0o);
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

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intt);
            }
        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt=new Intent(SearchActivity.this,RecyclerviewActivity.class);
                startActivity(intt);
            }
        });
    }
}
