package com.ringlayer.inputmysql;

/*
* Created by ringlayer on 26/11/18.
*/

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Random;

public class Util {
    Activity mContext;

    /* https://stackoverflow.com/questions/22371124/how-to-get-activity-context-into-a-non-activity-class-android/22371510 */
    public Util (Activity context)
    {
        try {
            mContext = context;


            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
            }
        }
        catch (Exception e) {
            Log.e("_do_log_debug", e.toString());
        }

    }



    /*
    * taken from
    * https://www.sitepoint.com/requesting-runtime-permissions-in-android-m-and-n/
    *
    */
    public void askForPermission(String permission, Integer requestCode) {
        try {
            if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mContext, permission)) {
                    ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);

                } else {
                    ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);
                }
            }
        }
        catch (Exception e) {
            Log.e("_do_log_debug", e.toString());
        }

    }
}
