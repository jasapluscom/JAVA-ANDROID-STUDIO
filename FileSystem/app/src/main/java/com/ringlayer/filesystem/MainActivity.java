package com.ringlayer.filesystem;

import android.Manifest;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {
    public Util util =  new Util(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            util.askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);
            util.askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2);
        }

        buat_dir("/sdcard/FileSystem");
        boolean ada = CheckIfDirectoryExists("/sdcard/FileSystem");

        if (ada == true) {
            createFileInSDCard("/sdcard/FileSystem/log.txt");
            if (check_file_exists("/sdcard/FileSystem/log.txt") == 1) {
                write("/sdcard/FileSystem/log.txt", "overwrite test");
                append_file("/sdcard/FileSystem/log.txt", "append test");
            }
        }
    }


    public int buat_dir(String Dirname) {
        int retme = 0;
        try {
            boolean exists = false;

            exists = CheckIfDirectoryExists(Dirname);
            if (exists == false) {
                new File(Dirname).mkdirs();
            }

        }
        catch (Exception e) {
            Log.e("error",e.toString());
        }

        return retme;
    }



    public void createFileInSDCard(String path) {
        try {
            int exists = check_file_exists(path);

            if (exists == 0) {
                File file = new File(path);
                file.createNewFile();
            }
        }
        catch (Exception e) {
            Log.e("error",e.toString());
        }
    }

    public boolean CheckIfDirectoryExists(String path) {
        boolean exists = false;
        try {

            File dir = new File(path);
            exists = dir.exists();
            return exists;
        }
        catch (Exception e) {
            Log.e("error",e.toString());
            return false;
        }
    }


    public boolean deleteDirectory(File path) {
        boolean delres = true;
        try {
            if (path.exists()) {
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
            delres = path.delete();
        }
        catch (Exception e) {
            Log.e("error",e.toString());
        }
        return delres;
    }

    public void copyFile(File sourceLocation, File targetLocation) {
        try {
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

        }
        catch (Exception e) {
            Log.e("error",e.toString());
        }
    }


    public void copyDirectory(File sourceLocation, File targetLocation) {
        try {
            if (sourceLocation.isDirectory()) {
                if (!targetLocation.exists()) {
                    targetLocation.mkdirs();
                }

                String[] children = sourceLocation.list();
                for (int i = 0; i < children.length; i++) {
                    copyDirectory(new File(sourceLocation, children[i]), new File(
                            targetLocation, children[i]));
                }
            } else {

                copyFile(sourceLocation, targetLocation);
            }

        }
        catch (Exception e) {
            Log.e("error",e.toString());
        }

    }

    public void append_file(String path, String content)
    {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(content);
            pw.close();
        }  catch(Exception e) {
            Log.e("error",e.toString());
        }
    }

    public String read(String fname){

        BufferedReader br = null;
        String line = null;

        try {
            br = new BufferedReader(new FileReader(fname));
            line = br.readLine();
            if (line == null) {
                line = "";
            }
        } catch(IOException e) {
            Log.e("error",e.toString());
            return null;
        }
        return line;
    }


    public Boolean write(String fname, String fcontent){
        try {

            String fpath =  fname;

            File file = new File(fpath);

            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fcontent);
            bw.close();

            Log.d("Suceess","Sucess");
            return true;

        } catch(IOException e) {
            Log.e("error",e.toString());
            return false;
        }

    }


    public List<String> listFolders(String directoryName) {
        List<String> DirAndFile = new ArrayList<String>();
        try {
            File directory = new File(directoryName);
            File[] fList = directory.listFiles();
            for (File file : fList) {
                if (file.isDirectory()) {
                    DirAndFile.add(file.getName());
                }
            }
        }
        catch (Exception e) {
            Log.e("error",e.toString());
        }
        return DirAndFile;
    }

    public int check_file_exists(String full_path) {
        int retme = 0;
        try {
            File file = new File(full_path);
            if(file.exists()) {
                retme = 1;
            }
        }
        catch (Exception e) {
            Log.e("error",e.toString());
        }

        return retme;
    }

}




