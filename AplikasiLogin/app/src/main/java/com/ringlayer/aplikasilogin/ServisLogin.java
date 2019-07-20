package com.ringlayer.aplikasilogin;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ringlayer on 18/03/19.
 */

public class ServisLogin extends IntentService {
    SendPostRequest spr = new SendPostRequest();
    private Toast mToastToShow;
    public ServisLogin() {
        super("ServisLogin");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Log.e("ohi", "login proc");
            String pesan = "";
            Bundle b = intent.getExtras();
            if (b != null) {
                String uname = (String) b.get("login");
                String passwd = (String) b.get("password");
                Log.e("login","login : " + uname);
                Log.e("passwd","passwd : " + passwd);
                String[] key_str = {"user", "pass"};
                String[] val_str = {uname, passwd};
                String res = spr._execute(getResources().getString(R.string.url),key_str, val_str);
                if (res.indexOf("success") != -1) {
                    pesan = "berhasil";
                }
                else {
                    pesan = "gagal";
                }
                publishResults(pesan);
            }


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

    private void publishResults(String pesan) {
        try {
            Log.e("[+] publishResults", pesan);
            Intent intent = new Intent("1");
            intent.putExtra("BROADCAST", pesan);
            sendBroadcast(intent);
        }
        catch (Exception e) {
            Log.e("publishResults", e.toString());
        }
    }
}
