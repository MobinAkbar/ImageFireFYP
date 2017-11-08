package com.education.imagefire;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.R.attr.entries;
import static android.R.attr.host;
import static com.education.imagefire.R.id.address;
import static com.education.imagefire.R.id.location;
import static com.education.imagefire.R.id.security;


/**
 * A simple {@link Fragment} subclass.
 */
public class FacilitiesFragment extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private TextView wifi;
    private TextView air_cond;
    private TextView breakfast;
    private TextView parking;
    private TextView recption;
    private TextView elctrition;
    private TextView jym;
    private TextView kitchn;
    private Facilities facilities;



    public FacilitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_facilities, container, false);
        String strtext = getArguments().getString("key");
        Toast.makeText(getContext(), "I have" + strtext, Toast.LENGTH_SHORT).show();

        wifi=(TextView)v.findViewById(R.id.v1);
        air_cond=(TextView)v.findViewById(R.id.v2);
        breakfast=(TextView)v.findViewById(R.id.v3);
        parking=(TextView)v.findViewById(R.id.v4);
        recption=(TextView)v.findViewById(R.id.v5);
        elctrition=(TextView)v.findViewById(R.id.v6);
        jym=(TextView)v.findViewById(R.id.v7);
        kitchn=(TextView)v.findViewById(R.id.v8);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query=databaseReference.child("Facilities").orderByChild("id").equalTo(strtext);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                  Facilities  facilities=ds.getValue(Facilities.class);

                    wifi.setText(facilities.getWifi());
                    air_cond.setText(facilities.getAci());
                    breakfast.setText(facilities.getBreakfast());
                    parking.setText(facilities.getParking());
                    recption.setText(facilities.getReception());
                    elctrition.setText(facilities.getElect());
                    jym.setText(facilities.getGym());
                    kitchn.setText(facilities.getKitchen());



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query.addValueEventListener(valueEventListener);


        return v;
        //return inflater.inflate(R.layout.fragment_facilities, container, false);
    }
}

