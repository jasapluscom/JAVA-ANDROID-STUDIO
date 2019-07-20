package com.ringlayer.capaudio;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/*
 * Created by ringlayer on 24/11/18.
*/

public class Util {
    Activity mContext;


    /* https://stackoverflow.com/questions/22371124/how-to-get-activity-context-into-a-non-activity-class-android/22371510 */
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
    /*
    * taken from
    * https://www.sitepoint.com/requesting-runtime-permissions-in-android-m-and-n/
    *
    * */
    public void askForPermission(String permission, Integer requestCode) {

        if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mContext, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);
            }
        }
    }
}
