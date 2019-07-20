package com.ringlayer.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {
    final BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
    TextView desc;
    public Util util =  new Util(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                util.askForPermission(Manifest.permission.BLUETOOTH, 1);
                util.askForPermission(Manifest.permission.BLUETOOTH_ADMIN, 2);
            }



            desc = (TextView) findViewById(R.id.desc);

            final Button on = (Button) findViewById(R.id.on);
            final Button discover = (Button) findViewById(R.id.discover);
            final Button off = (Button) findViewById(R.id.off);


            if (bluetooth == null) {
                Toast.makeText(this, "Bluetooth not supported on this device", Toast.LENGTH_LONG).show();
            }

            on.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    On();
                }
            });


            off.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Off();
                }
            });

            discover.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Discover();
                }
            });

        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    void On() {
        try {
            desc.setText("Bluetooth on");
            Log.e("on", "Turn on");
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Bluetooth On",Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Failed to turn on bluetooth",Toast.LENGTH_LONG).show();
            Log.e("error", e.toString());
        }
    }

    void Off() {
        try {
            desc.setText("Bluetooth off");
            bluetooth.disable();
            Toast.makeText(getApplicationContext(), "Bluetooth Off",Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    void Discover() {
        try {
            if (!bluetooth.isDiscovering()) {
                Toast.makeText(getApplicationContext(), "MAKING YOUR DEVICE DISCOVERABLE",
                        Toast.LENGTH_LONG);
                Intent EnableDiscoverable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(EnableDiscoverable,0);
                desc.setText("This device is discoverable");
            }
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

}
