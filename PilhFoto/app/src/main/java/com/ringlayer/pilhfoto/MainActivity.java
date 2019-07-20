package com.ringlayer.pilhfoto;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/*
 * Created by ringlayer on 24/11/18.
*/
public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public Util util =  new Util(MainActivity.this);
    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                util.askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);
                util.askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2);
            }
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new PilihFotoFragment(), "FOTO");
            ft.commit();
            ft.addToBackStack(null);
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

    public void PilihFoto() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
            }

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri uri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    String path = saveImage(bitmap);
                    PilihFotoFragment fotof = (PilihFotoFragment) getSupportFragmentManager().findFragmentByTag("FOTO");
                    fotof.SetFotoImageViewFromPath(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            Log.e("Err : ", e.toString());
        }
    }


    public String saveImage(Bitmap myBitmap) {
        String path = "";
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

            }
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            File TmpDirectory = new File(Environment.getExternalStorageDirectory() + "/foto");
            if (!TmpDirectory.exists()) {
                TmpDirectory.mkdirs();
            }
            try {
                File f = new File(TmpDirectory, "tmp_from_local.jpg");
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                MediaScannerConnection.scanFile(this,new String[]{f.getPath()}, new String[]{"image/jpeg"}, null);
                fo.close();
                path =  f.getAbsolutePath();
            }
            catch (Exception e) {
                Log.e("SaveImage Err : ", e.toString());
            }
        }
        catch (Exception e) {
            Log.e("mkdirs Err : ", e.toString());
        }
        return path;
    }
}
