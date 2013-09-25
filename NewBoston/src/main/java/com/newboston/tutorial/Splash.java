package com.newboston.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by Chris on 11.09.13.
 */
public class Splash extends Activity {

    MediaPlayer splashsound;
    int sleepingTime = 1000;
    @Override
    protected void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.splash);

        splashsound = MediaPlayer.create(this, R.raw.sparowsplash);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(getPrefs.getBoolean("spalshsound", false)){
            splashsound.start();
            sleepingTime = 3700;
        }


        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(sleepingTime);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent startSpalsh = new Intent(Splash.this, Menu.class);
                    startActivity(startSpalsh);
                }
            }
        };
        timer.start();
    }

    protected void onPause(){
        super.onPause();
        splashsound.release();
        finish();
    }
}
