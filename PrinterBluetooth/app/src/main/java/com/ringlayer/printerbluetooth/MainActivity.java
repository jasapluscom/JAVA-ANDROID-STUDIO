package com.ringlayer.printerbluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/*
* source code was modified from internet sample
* of bluetooth printer
*
* */

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;

    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;

    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    TextView myLabel;
    EditText myTextbox;
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

            Button openButton = (Button) findViewById(R.id.open);
            Button sendButton = (Button) findViewById(R.id.send);
            Button closeButton = (Button) findViewById(R.id.close);

            myLabel = (TextView) findViewById(R.id.label);
            myTextbox = (EditText) findViewById(R.id.entry);

            openButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    findBT();
                    openBT();
                }
            });


            sendButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sendData();
                }
            });


            closeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    closeBT();
                }
            });
        }
        catch(Exception e) {
            Log.e("error", e.toString());
        }
    }


    void findBT() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(mBluetoothAdapter == null) {
                myLabel.setText("No bluetooth adapter available");
            }

            if(!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if(pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    /* GANTI RPP300 DENGAN NAMA DEVICE PRINTER */
                    if (device.getName().equals("RPP300")) {
                        mmDevice = device;
                        break;
                    }
                }
            }

            myLabel.setText("Bluetooth device found.");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    void openBT()  {
        try {
            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();

            beginListenForData();

            myLabel.setText("Bluetooth Opened");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void beginListenForData() {
        try {
            final Handler handler = new Handler();

            // this is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {

                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {

                        try {

                            int bytesAvailable = mmInputStream.available();

                            if (bytesAvailable > 0) {

                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);

                                for (int i = 0; i < bytesAvailable; i++) {

                                    byte b = packetBytes[i];
                                    if (b == delimiter) {

                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );

                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        // tell the user data were sent to bluetooth printer device
                                        handler.post(new Runnable() {
                                            public void run() {
                                                myLabel.setText(data);
                                            }
                                        });

                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                        }

                    }
                }
            });

            workerThread.start();

        }
        catch(Exception e) {
            Log.e("error", e.toString());
        }
    }


    void sendData() throws IOException {
        try {
            String msg = myTextbox.getText().toString();
            msg += "\n";
            mmOutputStream.write(msg.getBytes());
            myLabel.setText("Data sent.");

        }
        catch(Exception e) {
            Log.e("error", e.toString());
        }
    }


    void closeBT() throws IOException {
        try {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
            myLabel.setText("Bluetooth Closed");
        }
        catch(Exception e) {
            Log.e("error", e.toString());
        }
    }

}
