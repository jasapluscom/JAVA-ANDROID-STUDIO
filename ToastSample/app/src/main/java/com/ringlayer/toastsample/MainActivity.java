package com.ringlayer.toastsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toast mToastToShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _popup("Contoh menampilkan toast di android");
    }


    public void _popup(String pesan) {
        try {
            mToastToShow = Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_LONG);
            mToastToShow.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);

            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mToastToShow.show();
                }
            }).start();

        }
        catch(Exception e) {
            Log.e("error", e.toString());
        }
    }

}
