package com.education.imagefire;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.key;
import static android.R.attr.value;

public class Owner_PortalActivity extends AppCompatActivity {

    TextView T1,T2,T3,T4;
    private DatabaseReference databaseReference;
    private List<Owner> imglist;
    private Owner owner;
    private ListView lv;
    private ProgressDialog progressDialog;
    private CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner__portal);

        T1=(TextView)findViewById(R.id.name);
        T2=(TextView)findViewById(R.id.numb1);
        T3=(TextView)findViewById(R.id.numb2);
        T4=(TextView)findViewById(R.id.email);
        String key=getIntent().getStringExtra("id");
        imglist=new ArrayList<>();
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(key);


        final ValueEventListener valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                   // owner=postSnapshot.getValue(Owner.class);
                   // imglist.add(postSnapshot.getValue(Owner.class));
                    //TODO get the data here

                }
               // adapter=new CarAdapter(ResultActivity.this,R.layout.list_item,imglistt);
                //lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        };

        query.addValueEventListener(valueEventListener);
//        T1.setText(owner.getName());
 //       T2.setText(owner.getNumber_1());
  //      T3.setText(owner.getNumber_2());
   //     T4.setText(owner.getEmail());


    }
}
