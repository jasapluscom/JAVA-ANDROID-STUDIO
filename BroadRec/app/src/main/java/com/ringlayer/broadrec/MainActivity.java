    package com.ringlayer.broadrec;

    import android.Manifest;
    import android.content.BroadcastReceiver;
    import android.content.Context;
    import android.content.Intent;
    import android.content.IntentFilter;
    import android.os.Build;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;

    public class MainActivity extends AppCompatActivity {
        public Util util =  new Util(MainActivity.this);
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                util.askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);
                util.askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2);
            }


            Intent intent = new Intent(MainActivity.this, Servis.class);
            startService(intent);
            stopService(intent);
        }

        private BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String act = "";
                final Context konteks = context;
                try {
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        act = bundle.getString("BROADCAST").trim();
                        Log.e("[+] pgonReceive", act);
                    }
                }
                catch (Exception e) {
                    Log.e("onReceive", e.toString());

                }
            }
        };


        @Override
        protected void onStart() {
            super.onStart();
            registerReceiver(br, new IntentFilter("1"));
        }

        @Override
        protected void onPause() {
            super.onPause();
            unregisterReceiver(br);
        }


    }
