package com.ringlayer.anekalayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Relative1Activity extends AppCompatActivity {
    Button back3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative1);
    }
    @Override
    protected void onStart() {
        super.onStart();
        try {
            back3 = (Button) findViewById(R.id.back3);
            back3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Back();
                }
            });
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    private void Back() {
        try {
            Intent intent = new Intent(Relative1Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e) {
            Log.e("err", e.toString());
        }
    }
}
