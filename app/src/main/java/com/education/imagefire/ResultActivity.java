package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
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

import static android.R.attr.host;
import static android.media.CamcorderProfile.get;
import static com.education.imagefire.R.drawable.hostel;
import static com.education.imagefire.R.drawable.map;
import static com.education.imagefire.R.id.address;
import static com.education.imagefire.R.id.location;
import static com.education.imagefire.R.id.security;

public class ResultActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<University> imglist;
    private List<Hostel> hostelList;
    private ListView lv;
    private String hostelid;
    int i;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId;
    String uniname,sexx,bhabi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//        firebaseAuth=FirebaseAuth.getInstance();
//        firebaseDatabase=FirebaseDatabase.getInstance();
//        databaseReference=firebaseDatabase.getReference();
//        FirebaseUser user=firebaseAuth.getCurrentUser();
//        UserId=user.getUid();
//        Toast.makeText(ResultActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();
//
//        uniname=getIntent().getStringExtra("name");
//        sexx=getIntent().getStringExtra("sex");
//
//        Toast.makeText(ResultActivity.this,"Name is "+uniname,Toast.LENGTH_SHORT).show();
//        Toast.makeText(ResultActivity.this,"Gender is "+sexx,Toast.LENGTH_SHORT).show();
//
//        imglist = new ArrayList<>();
//        hostelList = new ArrayList<>();
//        lv = (ListView) findViewById(R.id.recyclerView);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Hostel hostell=hostelList.get(i);
//                String id=hostell.getId();
//                String owner=hostell.getOwner();
//                String name=hostell.getName();
//                String adres=hostell.getAddres();
//                String urii=hostell.getUri();
//                Intent intent1235=new Intent(ResultActivity.this,StudentShowActivity.class);
//                intent1235.putExtra("uni_name",uniname);
//                intent1235.putExtra("Ownerid",owner);
//                intent1235.putExtra("Hostelid",id);
//                intent1235.putExtra("Hostelname",name);
//                intent1235.putExtra("Hosteladdress",adres);
//                intent1235.putExtra("Hosteluri",urii);
//                startActivity(intent1235);
//            }
//        });
//
//        String value = getIntent().getStringExtra("name");
//        Toast.makeText(ResultActivity.this, "i have" + value, Toast.LENGTH_SHORT).show();
//
//        for(i=0;i<3;i++) {
//
//            if(i==0){
//               bhabi="university_1";
//            }
//            if(i==1){
//                bhabi="university_2";
//            }
//            if(i==2){
//                bhabi="university_3";
//            }
//
//            DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
//            Query query1 = mFirebaseDatabaseReference.child("Hostel_Universities").orderByChild(bhabi).equalTo(value);
//
//            final ValueEventListener eventListener1 = new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
//
//                        University propertyInfo = ds.getValue(University.class);
//                        hostelid = propertyInfo.getId();
//                        Toast.makeText(ResultActivity.this, "here is " + hostelid, Toast.LENGTH_SHORT).show();
//                        imglist.add(propertyInfo);
//
//                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//                        Query query2 = databaseReference.child("Hostels").orderByChild("id").equalTo(hostelid);
//                        Toast.makeText(ResultActivity.this, " I HAVE SOMETHING 3", Toast.LENGTH_SHORT).show();
//                        final ValueEventListener eventListener2 = new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                    Toast.makeText(ResultActivity.this, " I HAVE SOMETHING 4", Toast.LENGTH_SHORT).show();
//                                    Hostel hostel = ds.getValue(Hostel.class);
//                                    String name = hostel.getName();
//                                   // if(hostel.getSex()==sexx){
//                                        hostelList.add(hostel);
//                                    //}
//                                    Toast.makeText(ResultActivity.this, "i have" + name, Toast.LENGTH_SHORT).show();
//                                   // hostelList.add(hostel);
//                                }
//                                CarAdapter adapter = new CarAdapter(ResultActivity.this, hostelList);
//                                lv.setAdapter(adapter);
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        };
//                        query2.addValueEventListener(eventListener2);
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            };
//            query1.addValueEventListener(eventListener1);
//        }
//       // CarAdapter adapter = new CarAdapter(ResultActivity.this, hostelList);
//        //lv.setAdapter(adapter);
//        if (imglist == null) {
//            Toast.makeText(ResultActivity.this, " I am propertylist, Empty ", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(ResultActivity.this, " I HAVE not 789 SOMETHING", Toast.LENGTH_SHORT).show();
//        }
//        if (hostelList == null) {
//            Toast.makeText(ResultActivity.this, " I am hostellist , Empty ", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(ResultActivity.this, " I HAVE not mobin SOMETHING", Toast.LENGTH_SHORT).show();
//        }
//
////        for (int i = 1; i <= imglist.size(); i++) {
////            PropertyInfo propertyInfo = imglist.get(i);
////            String pro_id = propertyInfo.getId();
//        Toast.makeText(ResultActivity.this, " I HAVE SOMETHING 2", Toast.LENGTH_SHORT).show();
////            Query query2 = mFirebaseDatabaseReference.child("Hostels").orderByChild("id").equalTo(hostelid);
////        Toast.makeText(ResultActivity.this, " I HAVE SOMETHING 3", Toast.LENGTH_SHORT).show();
////            final ValueEventListener eventListener2 = new ValueEventListener() {
////                @Override
////                public void onDataChange(DataSnapshot dataSnapshot) {
////                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
////                        Toast.makeText(ResultActivity.this, " I HAVE SOMETHING 4", Toast.LENGTH_SHORT).show();
////                        Hostel hostel = ds.getValue(Hostel.class);
////                        String name = hostel.getName();
////                        Toast.makeText(ResultActivity.this, "i have" + name, Toast.LENGTH_SHORT).show();
////                        hostelList.add(hostel);
////                    }
////                }
////
////                @Override
////                public void onCancelled(DatabaseError databaseError) {
////
////                }
////            };
////            query2.addValueEventListener(eventListener2);
//        //}
//
////        CarAdapter adapter = new CarAdapter(ResultActivity.this, hostelList);
////        lv.setAdapter(adapter);
//
////        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Intent ip = new Intent(ResultActivity.this, ShowdataActivity.class);
////                startActivity(ip);
////            }
////        });
//
//
////        CarAdapter adapter=new CarAdapter(ResultActivity.this,imglistt);
////        lv.setAdapter(adapter);
    }

}