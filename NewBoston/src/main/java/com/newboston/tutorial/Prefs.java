package com.newboston.tutorial;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Chris on 17.09.13.
 */
public class Prefs extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
