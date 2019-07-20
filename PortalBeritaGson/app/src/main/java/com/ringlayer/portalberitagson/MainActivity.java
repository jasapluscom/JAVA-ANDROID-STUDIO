package com.ringlayer.portalberitagson;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Gson gson;
    public Util util =  new Util(MainActivity.this);

    TextView text_title1;
    TextView text_title2;
    TextView text_title3;
    TextView text_title4;

    TextView text_content1;
    TextView text_content2;
    TextView text_content3;
    TextView text_content4;

    public static String string_json = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent PageIntent;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PageIntent = new Intent(this, ServisGetPage.class);
        startService(PageIntent);
        util.showToast("Loading news ... ", 10);
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String full_sesi = "no session";
            final Context konteks = context;
            final Intent msgIntent;
            try {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    String json = bundle.getString("json");
                    Log.e("[+] Splash onReceive", "GOT json  " + json);
                    if ( (json.contains("title1")) && (json.contains("title2")) && (json.contains("title3")) && (json.contains("title4")) ) {
                        text_title1 = (TextView)findViewById(R.id.title1);
                        text_title2 = (TextView)findViewById(R.id.title2);
                        text_title3 = (TextView)findViewById(R.id.title3);
                        text_title4 = (TextView)findViewById(R.id.title4);

                        text_content1 = (TextView)findViewById(R.id.content1);
                        text_content2 = (TextView)findViewById(R.id.content2);
                        text_content3 = (TextView)findViewById(R.id.content3);
                        text_content4 = (TextView)findViewById(R.id.content4);

                        Gson gson = new Gson();
                        NewsObj newsobj = new Gson().fromJson(json, NewsObj.class);

                        text_title1.setText(newsobj.title1);
                        text_title2.setText(newsobj.title2);
                        text_title3.setText(newsobj.title3);
                        text_title4.setText(newsobj.title4);

                        text_content1.setText(newsobj.content1);
                        text_content2.setText(newsobj.content2);
                        text_content3.setText(newsobj.content3);
                        text_content4.setText(newsobj.content4);
                    }
                    else {
                        util.showToast("Failed to get json news ! check your internet connection", 4);
                    }
                }
            }
            catch (Exception e) {
                Log.e("error", e.toString());
                Log.e("[-]Err", "Exception: " + Log.getStackTraceString(e));
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter("1"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }
}
