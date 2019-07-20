package com.ringlayer.portalberitagson;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ringlayer on 13/06/19.
 */

public class Util {
    Activity mContext;
    private static Context appContext;

    public Util (Activity context)
    {
        mContext = context;

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);}
    }

    public void askForPermission(String permission, Integer requestCode) {

        if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mContext, permission)) {
                ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);
            }
        }
    }

    public void showToast(String msg_to_display, int second_to_disp) {
        final Toast mToastToShow;
        int toastDurationInMilliSeconds = second_to_disp * 1000;

        mToastToShow = Toast.makeText(mContext, msg_to_display, Toast.LENGTH_LONG);
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
}
