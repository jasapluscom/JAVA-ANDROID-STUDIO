package com.ringlayer.cameracap;

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
import com.ringlayer.cameracap.R;
import com.ringlayer.cameracap.FotoFragment;
import com.ringlayer.cameracap.Util;
import java.io.File;
import static android.Manifest.permission.CAMERA;
/*
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
            ft.replace(R.id.frame, new FotoFragment(), "FOTO");
            ft.commit();
            ft.addToBackStack(null);
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

    public void AmbilFoto() {
        try {
            String imageFolderPath = Environment.getExternalStorageDirectory().toString() + "/foto";
            File imagesFolder = new File(imageFolderPath);
            imagesFolder.mkdirs();
            String imageName = "tmp_f.jpg";

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imageFolderPath, imageName)));
            startActivityForResult(takePictureIntent,0);
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 0 && resultCode == RESULT_OK) {
                String imageFolderPath = Environment.getExternalStorageDirectory().toString() + "/foto";

                String imageName = "tmp_f.jpg";
                String full_path = imageFolderPath + "/" + imageName;
                File imgFile = new  File(full_path);
                FotoFragment fotof = (FotoFragment) getSupportFragmentManager().findFragmentByTag("FOTO");
                fotof.SetFototoImageView();
            }
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }
}
