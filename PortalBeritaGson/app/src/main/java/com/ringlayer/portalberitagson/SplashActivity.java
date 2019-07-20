package com.ringlayer.portalberitagson;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    public Util util =  new Util(SplashActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                util.askForPermission(Manifest.permission.INTERNET, 1);
            }
        }
        catch (Exception e) {
            Log.e("error", e.toString());
            Log.e("[-]Err", "Exception: " + Log.getStackTraceString(e));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent msgIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(msgIntent);
                finish();
            }
        },4000);
    }
}
