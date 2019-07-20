package com.ringlayer.capvid;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.ringlayer.capvid.R;
import com.ringlayer.capvid.TakeVidFragment;
import com.ringlayer.capvid.Util;
import java.io.File;
import static android.Manifest.permission.CAMERA;

/**
 * Created by ringlayer on 24/11/18.
 */
public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public Util util =  new Util(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                util.askForPermission(CAMERA,0);
                util.askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);
                util.askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2);
            }
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new TakeVidFragment(), "VIDEO");
            ft.commit();
            ft.addToBackStack(null);
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

    public void RekamVideo() {
        try {
            Intent captureVideoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(captureVideoIntent, 1);
            File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/foto/tmp_v.mp4");

            Uri videoUri = Uri.fromFile(mediaFile);

            captureVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            startActivityForResult(captureVideoIntent, 1);
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 1 && resultCode == RESULT_OK) {
                String imageFolderPath = Environment.getExternalStorageDirectory().toString() + "/foto";
                Uri videoFileUri = data.getData();

                String video_name = "tmp_v.mp4";
                String full_path = imageFolderPath + "/" + video_name;
               
                Log.d("onActivityResult", full_path);

                File VideoFile = new  File(full_path);

                if(VideoFile.exists()) {
                    Toast.makeText(this, "Video has been saved to:\n" +
                            data.getData(), Toast.LENGTH_LONG).show();
                }

            }
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

}
