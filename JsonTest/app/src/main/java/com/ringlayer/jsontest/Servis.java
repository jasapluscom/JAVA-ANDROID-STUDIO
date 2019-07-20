package com.ringlayer.jsontest;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ringlayer on 16/03/19.
 */

public class Servis extends IntentService {
    public Servis() {
        super("Servis");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Log.e("servis ohi", "servis ohi");
            String base_url = getResources().getString(R.string.url);
            //get about
            Log.e("servis url", base_url);
            String json = getHtml(base_url);
            if (json.length() > 3) {
                Log.e("servis", json);
                publishResults(json);
            }

        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }


    /* modified from https://stackoverflow.com/questions/2423498/how-to-get-the-html-source-of-a-page-from-a-html-link-in-android */
    public static String getHtml(String url) {
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
            }
            URLConnection connection = (new URL(url)).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder html = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                html.append(line);
            }
            in.close();

            return html.toString();
        }
        catch (Exception e) {
            Log.e("[-] getHtml",e.toString());
            return "";
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