package com.newboston.tutorial;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

/**
 * Created by Chris on 01.10.13.
 */
public class WidgetConfig extends Activity implements View.OnClickListener {

    EditText info;
    AppWidgetManager awm;
    Context c;
    int awId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgetconfig);
        Button b = (Button) findViewById(R.id.bwidgetconfig);
        b.setOnClickListener(this);
        c = WidgetConfig.this;
        info = (EditText) findViewById(R.id.etwidgetconfig);

        //getting info about the launched widget
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            awId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            awm = AppWidgetManager.getInstance(c);
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        RemoteViews rview = new RemoteViews(c.getPackageName(), R.layout.widget);
        rview.setTextViewText(R.id.tvConfigInput, info.getText().toString());

        Intent i = new Intent(c, Splash.class);
        PendingIntent pi = PendingIntent.getActivity(c, 0, i, 0);
        rview.setOnClickPendingIntent(R.id.bwidgetOpen, pi);
        awm.updateAppWidget(awId, rview);

        Intent result = new Intent();
        result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awId);
        setResult(RESULT_OK, result);
        finish();
    }
}

