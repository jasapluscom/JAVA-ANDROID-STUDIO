package com.ringlayer.servis;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public Util util =  new Util(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        util.askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);
        util.askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2);

        Intent msgIntent = new Intent(this, IntenServis.class);
        msgIntent.putExtra(IntenServis.PARAM_IN_MSG, "");
        startService(msgIntent);
    }


}









