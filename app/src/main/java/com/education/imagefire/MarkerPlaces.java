package com.education.imagefire;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NET LINK on 11/24/2017.
 */

public class MarkerPlaces {
    public static void showMarker(Context c, GoogleMap mMap, List<LatLng> list) {

//        @Override
//        public void onMapReady ( final GoogleMap googleMap){

           // mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(list.get(i));
                    mMap.addMarker(markerOptions);

                }
            }


        }
    }
