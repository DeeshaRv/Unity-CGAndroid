package com.dirtcube.androidintegrationunity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        String finalLocation = copyFile("house.hub");
        mUnityPlayer.UnitySendMessage("Manager", "NativeMessageLocal",finalLocation);
    }

    private String copyFile(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            String newFileName = new File(getFilesDir(),File.separator+"house.hub").getAbsolutePath();
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;

            return newFileName;
        } catch (Exception e) {
            Log.e("CopyException", e.getMessage());
        }

        return null;
    }

    /*Call this event */
    public void gameStarted(String levelNumber){

    }

}