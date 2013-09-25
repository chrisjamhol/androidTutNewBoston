package com.newboston.tutorial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Chris on 18.09.13.
 */
public class GFX extends Activity {

    MyBringBack ourView;
    PowerManager.WakeLock wl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //wake-lock
        PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
        super.onCreate(savedInstanceState);
        wl.acquire();
        //full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ourView = new MyBringBack(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }

}
