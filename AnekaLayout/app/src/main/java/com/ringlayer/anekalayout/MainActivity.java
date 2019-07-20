package com.ringlayer.anekalayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button lin1;
    Button lin2;
    Button rel1;
    Button rel2;
    Button constrain;
    Button table;
    Button constrain2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            lin1 = (Button) findViewById(R.id.lin1);
            lin2 = (Button) findViewById(R.id.lin2);
            rel1 = (Button) findViewById(R.id.rel1);
            rel2 = (Button) findViewById(R.id.rel2);
            constrain = (Button) findViewById(R.id.tb_constrain);
            table = (Button) findViewById(R.id.tb_table);
            constrain2 = (Button) findViewById(R.id.tb_constrain2);

            lin1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Linear1();
                }
            });
            lin2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Linear2();
                }
            });
            rel1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Relative1();
                }
            });
            rel2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Relative2();
                }
            });
            constrain.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Constrain();
                }
            });
            table.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Table();
                }
            });
            constrain2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Constrain2();
                }
            });

        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
    }

    void Linear1() {
        try {
            Intent intent = new Intent(MainActivity.this, Linear1Activity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e) {
            Log.e("err", e.toString());
        }
    }

    void Linear2() {
        try {
            Intent intent = new Intent(MainActivity.this, Linear2Activity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e) {
            Log.e("err", e.toString());
        }
    }

    void Relative1() {
        try {
            Intent intent = new Intent(MainActivity.this, Relative1Activity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e) {
            Log.e("err", e.toString());
        }
    }

    void Relative2() {
        try {
            Intent intent = new Intent(MainActivity.this, Relative2Activity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e) {
            Log.e("err", e.toString());
        }
    }

    void Constrain() {
        try {
            Intent intent = new Intent(MainActivity.this, ConstrainActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e) {
            Log.e("err", e.toString());
        }
    }

    void Constrain2() {
        try {
            Intent intent = new Intent(MainActivity.this, ConstraintComplexActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e) {
            Log.e("err", e.toString());
        }
    }

    void Table() {
        try {
            Intent intent = new Intent(MainActivity.this, TableActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e) {
            Log.e("err", e.toString());
        }
    }

}
