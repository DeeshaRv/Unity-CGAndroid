package com.dirtcube.androidintegrationunity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public abstract class UnityHelperActivity extends UnityPlayerActivity {

    abstract public void levelStarted(int levelNumber);
    abstract public void levelCompleted(int levelNumber);
    abstract public void levelFailed(int levelNumber);
    abstract public void gameEnded(int lastLevelNumber);
    abstract public void soundSettingChanged(boolean isSoundOn);
    abstract public void gameExit();
    abstract public void gameLoaded();
    //abstract public void gameLoadingValue(int percentage);
    //abstract public void submitScore(int scoreValue);

    //NOTE: BEFORE passing the asset bundle pass the sound related function --- KEY "SetDefaultSound".
    // Pass true or false as a string in sendMessage to send soundSetting preference to unity game at launch.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String finalLocation = copyFileFromAssetToCache("house.hub");
        mUnityPlayer.UnitySendMessage("Manager", "NativeMessageLocal",finalLocation);
    }

    private String copyFileFromAssetToCache(String filename) {
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

    /*Call this event when game play of a new level starts
    * VAR: levelNumber is the numeric value of the level started
    * CALL THIS FUNCTION AGAIN IF THE LEVEL HAS RE-STARTED */
    //    public void gameStarted(int levelNumber){
//        Log.d("UnityLog: ","gameStarted--"+levelNumber);
//    }

    /*Call this event when game play of a level is completed successfully
     * VAR: levelNumber is the numeric value of the level completed*/
    //    public void gameCompleted(int levelNumber){
//        Log.d("UnityLog: ","gameCompleted--"+levelNumber);
//
//    }

    /*Call this event when game play of a level has failed
    * VAR: levelNumber is the numeric value of the level failed*/
    //    public void gameFailed(int levelNumber){
//        Log.d("UnityLog: ","gameFailed--"+levelNumber);
//
//    }

    /*Call this event last game level is completed
    * call it after gameCompleted and submitScore functions being called.
    * VAR: lastLevelNumber is the numeric value of the level completed*/
    //    public void gameEnded(int lastLevelNumber){
//        Log.d("UnityLog: ","gameEnded--"+lastLevelNumber);
//
//    }

    /*Call this event when user changes sound setting
    * VAR: isSoundOn should be true if sound is switched ON
    * VAR: isSoundOn should be false if sound is switched OFF*/
//    public void soundSettingChanged(boolean isSoundOn){
//        Log.d("UnityLog: ","soundSettingChanged--"+isSoundOn);
//
//    }

    /*Call this event when the user exits the game by pressing back from the main screen*/
//    public void gameExit(){
//        Log.d("UnityLog: ","gameExit");
//
//    }

    /*Call this event when the game has loaded successfully and ready to interact with*/
//    public void gameLoaded(){
//        Log.d("UnityLog: ","gameLoaded--");
//
//    }

    /*Call this event when the game has loaded successfully and ready to interact with
    * VAR: percentage is the %value of the loaded game.
    * If the game is having heavy resources to load then this shall be
    * logged to pass the update to the loading screen*/
//    public void gameLoadingValue(int percentage){
//        Log.d("UnityLog: ","gameLoadingValue--"+percentage);
//    }

    /* Call this event to pass the game score after calling gameCompleted function.
    * VAR: scoreValue is the integer value of the score given to the player */
//    public void submitScore(int scoreValue){
//        Log.d("UnityLog: ","submitScore--"+scoreValue);
//
//    }

}