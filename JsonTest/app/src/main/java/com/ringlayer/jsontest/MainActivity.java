package com.ringlayer.jsontest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Log.e("main run", "main run");
        }
        catch (Exception e) {
            Log.e("error:", e.toString());
        }
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String act = "";
            final Context konteks = context;
            try {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    act = bundle.getString("BROADCAST").trim();
                    Log.e("[+] pgonReceive", act);
                    JSONObject reader = new JSONObject(act);
                    String title_teks = reader.getString("title");
                    String contain_teks = reader.getString("contain");
                    String full_str = title_teks + "<br>" + contain_teks;
                    Log.e("title_teks", title_teks);
                    Log.e("contain_teks", contain_teks);
                    TextView json = (TextView) findViewById(R.id.json);
                    json.setText(Html.fromHtml(full_str));
                }
                else {
                    Log.e("fail", "no broadcast received");
                }
            }
            catch (Exception e) {
                Log.e("onReceive", e.toString());

            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(br, new IntentFilter("1"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

}
