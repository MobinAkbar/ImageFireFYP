package com.education.imagefire;

import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.id;
import static android.R.attr.key;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{
    private GoogleMap mMap;
    private DatabaseReference databaseReference;
    private String nameMap;
    private String ltitude;
    private String logitude;
    private double lat;
    private double logi;
    //private Map map;
    private static final LatLng COMSATS=new LatLng(33.6518,73.1566);
    //private static final LatLng HOSTEL=new LatLng(lat,);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        nameMap=getIntent().getStringExtra("name");
        ltitude=getIntent().getStringExtra("lati");
        logitude=getIntent().getStringExtra("longi");

        lat=Double.parseDouble(ltitude);
        logi=Double.parseDouble(logitude);



        //String id=getIntent().getStringExtra("key");
        Toast.makeText(MapsActivity.this,"I have"+id,Toast.LENGTH_SHORT).show();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       // databaseReference= FirebaseDatabase.getInstance().getReference();
        //Query query=databaseReference.child("Map").orderByChild(id).equalTo(id);

        //final ValueEventListener eventListener=new ValueEventListener() {
       // databaseReference.addValueEventListener(new ValueEventListener() {
          //  @Override
            //public void onDataChange(DataSnapshot dataSnapshot) {
              //  for(DataSnapshot ds:dataSnapshot.getChildren()){
                //    map=ds.getValue(Map.class);

                //}
            //}

            //@Override
            //public void onCancelled(DatabaseError databaseError) {

            //}
        //};

        //query.addValueEventListener(eventListener);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng location=new LatLng(logi ,lat );


        PolylineOptions options=new PolylineOptions().add(COMSATS).add(location).width(5).color(Color.BLUE).geodesic(true);
              googleMap.addPolyline(options);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(COMSATS,13));
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot s:dataSnapshot.getChildren()){
//                    ImageUpload user=s.getValue(ImageUpload.class);
                    //LatLng location=new LatLng(logi ,lat );

                    //googleMap.addMarker(new MarkerOptions().position(location)
                      //      .title(nameMap));
                    //googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));




                }
//    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
//        double distance = 0;
//        Location locationA = new Location("A");
//        locationA.setLatitude(LatLng1.latitude);
//        locationA.setLongitude(LatLng1.longitude);
//        Location locationB = new Location("B");
//        locationB.setLatitude(LatLng2.latitude);
//        locationB.setLongitude(LatLng2.longitude);
//        distance = locationA.distanceTo(locationB);
//
//        return distance;
//    }
           // }

         //   @Override
           // public void onCancelled(DatabaseError databaseError) {

           // }


       // });


   // }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

}
