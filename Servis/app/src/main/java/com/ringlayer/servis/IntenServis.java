package com.ringlayer.servis;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.File;


public class IntenServis extends IntentService {
    public static final String PARAM_IN_MSG = "";
    public static final String PARAM_OUT_MSG = "";

    public IntenServis() {
        super("IntenServis");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(5000);
            Log.i("info", "[+] intent servis mulai dijalankan");
            buat_dir("intenservis");
        }
        catch (Exception e) {
            Log.e("error", e.toString());
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


}