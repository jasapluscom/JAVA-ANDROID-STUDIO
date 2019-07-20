package com.ringlayer.capaudio;

import android.Manifest;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import static android.Manifest.permission.CAMERA;
/*
 * Created by ringlayer on 24/11/18.
*/

public class MainActivity extends AppCompatActivity {
    private MediaRecorder recorder = new MediaRecorder();
    public Util util =  new Util(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            /*
            *   if (Build.VERSION.SDK_INT >= 23) {
            */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                util.askForPermission(Manifest.permission.RECORD_AUDIO,0);
                util.askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);
                util.askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2);
            }


            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new TakeAudioFragment(), "AUDIO");
            ft.commit();
            ft.addToBackStack(null);
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

    public void RekamAudio() {
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/foto/tmp_a.mp3";
            File mediaFile = new File(path);

            //recorder.setMaxDuration(300000);
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            recorder.setOutputFile(path);
            recorder.prepare();
            recorder.start();
            Toast.makeText(MainActivity.this, "Recording Started",
                    Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

    public void StopRekamAudio() {
        try {

            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/foto/tmp_a.mp3";
            File mediaFile = new File(path);
            recorder.stop();
            Toast.makeText(MainActivity.this, "Recording Completed, audio saved to : " + path,
                    Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }


}
