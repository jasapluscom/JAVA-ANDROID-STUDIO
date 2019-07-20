package com.ringlayer.jsontest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            Log.e("Splash", "Splash run");
            Intent intent = new Intent(Splash.this, Servis.class);
            startService(intent);
            stopService(intent);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },1000);
        }
        catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }
}
