package com.ringlayer.gpssample;

import android.Manifest;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GpsActivity extends AppCompatActivity {
    public Util util =  new Util(GpsActivity.this);
    TextView tekslatitude;
    TextView tekslongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                util.askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, 1);
            }
            GetLocation(GpsActivity.this);
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    public void GetLocation(Context myContext) {
        try {
            LocationManager lm;
            Location l;
            String provider;
            double lng = 0;
            double lat = 0;
            String lat_str;
            String long_str;

            lm = (LocationManager) myContext.getSystemService(Context.LOCATION_SERVICE);
            Criteria c = new Criteria();
            provider = lm.getBestProvider(c, false);
            l = lm.getLastKnownLocation(provider);
            if (l != null) {
                lng = l.getLongitude();
                lat = l.getLatitude();
                long_str = "Longitude : " + String.valueOf(lng);
                lat_str = "Latitude : " + String.valueOf(lat);
                tekslatitude = (TextView) findViewById(R.id.latitude);
                tekslatitude.setText(long_str);
                Log.d("long", long_str);

                tekslongitude = (TextView) findViewById(R.id.longitude);
                tekslongitude.setText(lat_str);
                Log.d("lat", lat_str);
            }
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            final Button update = (Button) findViewById(R.id.update);
            update.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    GetLocation(GpsActivity.this);
                }
            });
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }
}
