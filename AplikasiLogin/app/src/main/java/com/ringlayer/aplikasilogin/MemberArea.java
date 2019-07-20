package com.ringlayer.aplikasilogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MemberArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_area);

        Toast toas = Toast.makeText(this, "Welcome to member area", Toast.LENGTH_LONG);
    }
}
