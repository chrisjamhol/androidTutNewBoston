package com.newboston.tutorial;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Chris on 01.10.13.
 */
public class PointlessWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Random r = new Random();
        int randomInt = r.nextInt(100000000);

        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            RemoteViews v = new RemoteViews(context.getPackageName(), R.layout.widget);
            v.setTextViewText(R.id.tvwidgetUpdate, String.valueOf(randomInt));
            appWidgetManager.updateAppWidget(appWidgetIds[i], v);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context, "deleted :)", Toast.LENGTH_SHORT).show();
    }
}
