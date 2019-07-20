package com.ringlayer.inputmysql;

import android.Manifest;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {
    public Util util =  new Util(MainActivity.this);
    SendPostRequest spr = new SendPostRequest();
    private Toast mToastToShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            util.askForPermission(Manifest.permission.INTERNET, 3);
        }

        final Button go = (Button) findViewById(R.id.submit);
        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Submit();
            }
        });
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
            EditText e_nama;
            EditText e_pesan;
            String  nama, pesan;

            showToast("Proses pengiriman data", 4);
            e_nama = (EditText) findViewById(R.id.nama);
            e_pesan = (EditText) findViewById(R.id.pesan);
            nama = e_nama.getText().toString();
            pesan = e_pesan.getText().toString();
            String[] key_str = {"nama", "pesan"};
            String[] val_str = {nama, pesan};
            String res = spr._execute(getResources().getString(R.string.url),key_str, val_str);
            if (res.indexOf("success") != -1) {
                showToast("input berhasil", 3);
            }
            else {
                showToast("input gagal diinput", 3);
            }
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }
}
