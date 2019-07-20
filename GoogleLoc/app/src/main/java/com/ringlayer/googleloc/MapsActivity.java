package com.ringlayer.googleloc;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        Context myContext = MapsActivity.this;
        LocationManager lm;
        Location l;
        String provider;
        double lng = 0;
        double lat = 0;
        lm = (LocationManager) myContext.getSystemService(Context.LOCATION_SERVICE);
        Criteria c = new Criteria();
        provider = lm.getBestProvider(c, false);
        l = lm.getLastKnownLocation(provider);
        if (l != null) {
            lng = l.getLongitude();
            lat = l.getLatitude();
        }
        mMap = googleMap;
        LatLng curpos = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(curpos).title("current position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curpos));
    }
}
