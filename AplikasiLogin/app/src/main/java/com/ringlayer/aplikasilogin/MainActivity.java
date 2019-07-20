package com.ringlayer.aplikasilogin;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toast mToastToShow;
    public Util util =  new Util(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            util.askForPermission(Manifest.permission.INTERNET, 3);

            final Button go = (Button) findViewById(R.id.tombol_login);
            go.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Submit();
                }
            });
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }
    public void showToast(String msg_to_display, int second_to_disp) {
        int toastDurationInMilliSeconds = second_to_disp * 1000;
        mToastToShow = Toast.makeText(this, msg_to_display, Toast.LENGTH_LONG);
        mToastToShow.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 /*Tick duration*/) {
            public void onTick(long millisUntilFinished) {
                mToastToShow.show();
            }
            public void onFinish() {
                mToastToShow.cancel();
            }
        };

        mToastToShow.show();
        toastCountDown.start();
    }


    void Submit() {
       try {
           EditText e_username;
           EditText e_password;

           showToast("Proses login", 4);
           e_username = (EditText) findViewById(R.id.username);
           e_password = (EditText) findViewById(R.id.password);

           Intent msgIntent = new Intent(this, ServisLogin.class);
           msgIntent.putExtra("login", e_username.getText().toString());
           msgIntent.putExtra("password", e_password.getText().toString());

           startService(msgIntent);
       }
       catch (Exception e) {
           Log.e("error", e.toString());
       }
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
                    if (act.indexOf("berhasil") != -1) {
                        Intent intentmem = new Intent(MainActivity.this, MemberArea.class);
                        startActivity(intentmem);
                        finish();
                    }
                    else {
                        showToast("Login Success", 4);
                    }
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
