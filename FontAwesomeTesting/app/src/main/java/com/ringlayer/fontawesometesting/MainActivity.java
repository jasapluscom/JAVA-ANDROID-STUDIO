package com.ringlayer.fontawesometesting;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Button fontAwesomeUnLockAndString = (Button) findViewById(R.id.tombol_unlock);
        fontAwesomeUnLockAndString.setTypeface(fontAwesomeFont);
    }
}
