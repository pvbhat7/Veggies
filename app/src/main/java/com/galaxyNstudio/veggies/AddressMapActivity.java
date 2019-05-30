package com.galaxyNstudio.veggies;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class AddressMapActivity extends FragmentActivity implements
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener,
        OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    Location loc;
    Button mapContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address_map);
        mapContinueButton=findViewById(R.id.mapContinueButton);
        mapContinueButton.setOnClickListener(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                loc=location;
                                // Logic to handle location object
                            }
                        }
                    });
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        mMap.setOnCameraIdleListener(this);
        mMap.setOnCameraMoveStartedListener(this);
        mMap.setOnCameraMoveListener(this);
        mMap.setOnCameraMoveCanceledListener(this);

        // Show Sydney on the map.
        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(-33.87365, 151.20689), 10));
    }

    @Override
    public void onCameraMoveStarted(int reason) {

        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            Toast.makeText(this, "The user gestured on the map.",
                    Toast.LENGTH_SHORT).show();
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_API_ANIMATION) {
            Toast.makeText(this, "The user tapped something on the map.",
                    Toast.LENGTH_SHORT).show();
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_DEVELOPER_ANIMATION) {
            Toast.makeText(this, "The app moved the camera.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCameraMove() {
        Toast.makeText(this, "The camera is moving.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraMoveCanceled() {
        Toast.makeText(this, "Camera movement canceled.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraIdle() {
        Toast.makeText(this, "The camera has stopped moving.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mapContinueButton:
                Intent intent = new Intent(AddressMapActivity.this, HomePage.class);
                startActivity(intent);
                break;
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            mMap=googleMap;
            mMap.setMyLocationEnabled(true);
            googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {


                    if(cameraPosition != null){
                        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude));
                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(18.0f);
                        mMap.moveCamera(center);
                        mMap.animateCamera(zoom);
                        Toast.makeText(AddressMapActivity.this,cameraPosition.target.latitude+""+cameraPosition.target.longitude,Toast.LENGTH_LONG).show();
                    }else if(loc!=null){
                        //mMap.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(),loc.getLongitude())).title("It's Me!"));
                        CameraUpdate center=CameraUpdateFactory.newLatLng(new LatLng(loc.getLatitude(), loc.getLongitude()));
                        CameraUpdate zoom=CameraUpdateFactory.zoomTo(18.0f);
                        mMap.moveCamera(center);
                        mMap.animateCamera(zoom);
                    }

                }
            });


        }*/






    }

    /*    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            //Toast.makeText(this, "LOCATION PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            mMap.setMyLocationEnabled(true);

            *//*if (mMap.getMyLocation() != null) {
                LatLng currentLocation = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

                mMap.addMarker(new MarkerOptions().position(currentLocation).draggable(true));
            }*//*
            // Check if we were successful in obtaining the map.
            if (mMap != null) {


                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

                    @Override
                    public void onMyLocationChange(Location arg0) {
                        // TODO Auto-generated method stub

                        mMap.clear();
                        if(loc!=null){
                            mMap.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(),loc.getLongitude())).title("It's Me!"));
                            CameraUpdate center=CameraUpdateFactory.newLatLng(new LatLng(loc.getLatitude(), loc.getLongitude()));
                            CameraUpdate zoom=CameraUpdateFactory.zoomTo(17.0f);
                            mMap.moveCamera(center);
                            mMap.animateCamera(zoom);
                        }

                }});



            }
        }
    }

*/








