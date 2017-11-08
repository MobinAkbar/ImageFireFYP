package com.education.imagefire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

//                DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
//                Query query1 = mFirebaseDatabaseReference.child("Hostel_Property_Info").orderByChild("university_1").equalTo(answer);
//                Toast.makeText(SearchActivity.this, "here is i am" , Toast.LENGTH_SHORT).show();
//                final ValueEventListener eventListener1=new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            PropertyInfo propertyInfo = ds.getValue(PropertyInfo.class);
//                            hostelid = propertyInfo.getId();
//                            imglistt.add(propertyInfo);
//                            Toast.makeText(SearchActivity.this, "here is " + hostelid, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                };
//                query1.addValueEventListener(eventListener1);
//
//                if (imglistt == null) {
//                    Toast.makeText(SearchActivity.this," Please Make A Selection ", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
//                    intent.putExtra("List",(Serializable) imglistt);
//                    startActivityForResult(intent, 0);
//                }
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
