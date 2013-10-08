package com.newboston.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import java.util.Locale;
import java.util.Random;

/**
 * Created by Chris on 07.10.13.
 */
public class TextVoice extends Activity implements View.OnClickListener {

    static final String[] texts = {
            "Whaaaat's uuuuup!", "Good Day", "You smell!", "Biatch!"
    };

    TextToSpeech tts;

    @Override
    protected void onPause() {
        super.onPause();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textvoice);
        Button b = (Button) findViewById(R.id.bTextToVoice);
        b.setOnClickListener(this);
        tts = new TextToSpeech(TextVoice.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Random r = new Random();
        tts.speak(texts[r.nextInt(4)], TextToSpeech.QUEUE_FLUSH, null);
    }
}
