package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    Button B1,B2,B3;
    EditText E1;
    private DatabaseReference databaseReference;
    private List<PropertyInfo> imglistt;
    private String hostelid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imglistt=new ArrayList<>();

        drawerLayout=(DrawerLayout)findViewById(R.id.drawr);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        B1=(Button)findViewById(R.id.go);
        B3=(Button)findViewById(R.id.go0o);
        E1=(EditText)findViewById(R.id.eee1);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer=E1.getText().toString();

                Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                    intent.putExtra("name",answer);
                    startActivity(intent);

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
