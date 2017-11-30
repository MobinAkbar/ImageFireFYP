package com.education.imagefire;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.provider.Settings;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.R.attr.key;
import static com.education.imagefire.R.id.location;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String nameMap;
    private String id;

    private String lat_s;
    private String long_s;
    private String lat_t;
    private String long_t;

    private Double lat_u;
    private Double long_u;
    private Double lat_v;
    private Double long_v;

    private double lat123;
    private double logi123;
    private static final LatLng UMT = new LatLng(31.4512, 74.2930);
    private static final LatLng UET = new LatLng(31.5770, 74.3552);
    private static final LatLng COMSATS = new LatLng(31.6003, 74.3952);
    private static final LatLng PUNJAB = new LatLng(31.5546, 74.3572);
    private static final LatLng FAST = new LatLng(31.4812, 74.3031);
    String univrsty99;
    private Map map;
    String ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        id = getIntent().getStringExtra("id");
        lat_s=getIntent().getStringExtra("lati");
        long_s=getIntent().getStringExtra("longi");
        lat_t=getIntent().getStringExtra("A_lati1");
        long_t=getIntent().getStringExtra("A_longi1");

        lat_u=Double.parseDouble(lat_s);
        long_u=Double.parseDouble(long_s);
        lat_v=Double.parseDouble(lat_t);
        long_v=Double.parseDouble(long_t);

        Toast.makeText(MapsActivity.this, "I have" + id, Toast.LENGTH_SHORT).show();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("NearbyPlaces").child(id);

        Toast.makeText(MapsActivity.this, "I have starting query ", Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    map = ds.getValue(Map.class);
                    lat123 = map.getLatitude();
                    logi123 = map.getLongitude();
                    String name12345=map.getName();

                    LatLng latLng = new LatLng(lat123, logi123);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng).title(name12345);
                    googleMap.addMarker(markerOptions);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        LatLng location = new LatLng(lat_u, long_u);
        LatLng location2 = new LatLng(lat_v, long_v);

        PolylineOptions options = new PolylineOptions().add(location).add(location2).width(5).color(Color.BLUE).geodesic(true);
        googleMap.addPolyline(options);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13));

//                    Map map=ds.getValue(Map.class);
//                    String name=map.getName();
//                  double ltitude22=map.getLatitude();
//                    double logitude22=map.getLongitude();
//                    LatLng location=new LatLng(ltitude22 ,logitude22 );
//
//                    googleMap.addMarker(new MarkerOptions().position(location)
//                          .title(name));
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        //   LatLng latLng=array1.get(i);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(COMSATS);
        googleMap.addMarker(markerOptions);
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

}
