package com.travel.smartcitytraveller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CitySlt extends AppCompatActivity implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_slt);


        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Travel Plan"); // for set actionbar title

        actionBar.setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.csmap);
        mapFragment.getMapAsync(this);

        /*
        public boolean onOptionsItemSelected(MenuItem item) {
            // TODO Auto-generated method stub
            int id = item.getItemId();
            if (id == android.R.id.home) {
                finish();
            }*/

           // return super.onOptionsItemSelected(item);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Assign map
        GoogleMap mMap=googleMap;
        // move the camera to India
        LatLng India = new LatLng(21, 72);
        //move camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(India));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //asssign var
                List<Address> addresses = null;
                //creating marker
                MarkerOptions markerOptions=new MarkerOptions();
                //Set marker
                markerOptions.position(latLng);
                //get city
                Geocoder gcd = new Geocoder(CitySlt.this, Locale.getDefault());
                try {
                   addresses = gcd.getFromLocation(latLng.latitude,latLng.longitude, 1);
                    //set city name on map
                    if (addresses!=null & addresses.size() > 0) {
                        markerOptions.title(addresses.get(0).getLocality());
                        // System.out.println(addresses.get(0).getLocality());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //clear prev marker
                mMap.clear();
                //zoom map to the marker
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                //add marker
                mMap.addMarker(markerOptions);
            }
        });


    }
}