package com.ringlayer.portalberitagson;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ringlayer on 13/06/19.
 */


public class ServisGetPage extends IntentService {
    public static final String PARAM_OUT_MSG = "";
    public ServisGetPage() {
        super("ServisGetPage");
    }
    private Handler handler;

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            String json_url = "https://raw.githubusercontent.com/jasapluscom/image_resources/master/data.json";

            String json = getHtml(json_url);
            publishResults(json);


        } catch (Exception e) {
            Log.e("[-]ohi getpage", e.toString());
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
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


    private void publishResults(String json) {
        try {
            Log.e("[+] publishResults", "done");
            Intent intent = new Intent("1");
            intent.putExtra("json", json);
            sendBroadcast(intent);
        }
        catch (Exception e) {
            Log.e("publishResults", e.toString());
        }

    }
}
