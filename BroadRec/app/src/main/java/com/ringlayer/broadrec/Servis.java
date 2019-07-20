package com.ringlayer.broadrec;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

/**
 * Created by ringlayer on 15/03/19.
 */

public class Servis extends Service {
    public static final String PARAM_IN_MSG = "";
    protected void onHandleIntent(Intent intent) {
        try {
            String pesan = "";
            Log.e("[-] ohi", PARAM_IN_MSG);
            Thread.sleep(5000);
            buat_dir("/sdcard/broadrec");
            boolean exists = CheckIfDirectoryExists("/sdcard/broadrec");
            if (exists) {
                pesan = "berhasil";
            }
            else {
                pesan = "gagal";
            }
            publishResults(pesan);

        }
        catch (Exception e) {
            Log.e("onHandleIntent ServisCekSesi", "Exception: " + Log.getStackTraceString(e));
        }
    }

    public int buat_dir(String Dirname) {
        int retme = 0;
        try {
            boolean exists = false;

            exists = CheckIfDirectoryExists(Dirname);
            if (exists == false) {
                new File(Dirname).mkdirs();
            }

        }
        catch (Exception e) {
            Log.e("error",e.toString());
        }

        return retme;
    }


    public boolean CheckIfDirectoryExists(String path) {
        boolean exists = false;
        try {

            File dir = new File(path);
            exists = dir.exists();
            return exists;
        }
        catch (Exception e) {
            Log.e("error",e.toString());
            return false;
        }
    }

    @Override
    public IBinder onBind(Intent i) {
        return null;
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
