package com.newboston.tutorial;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Chris on 19.09.13.
 */
public class SoundStuff extends Activity implements View.OnClickListener,View.OnLongClickListener{
    SoundPool sp;
    int explosion = 0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnLongClickListener(this);
        v.setOnClickListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        explosion = sp.load(this,R.raw.explosion,1);
        mp = MediaPlayer.create(this, R.raw.sparowsplash);
    }

    @Override
    public void onClick(View v) {
        if(explosion != 0)
            sp.play(explosion,1,1,0,0,1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sp.release();
        mp.release();
    }

    @Override
    public boolean onLongClick(View v) {
        mp.start();
        return false;
    }
}
