package com.newboston.tutorial;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chris on 07.10.13.
 */
public class StatusBar extends Activity implements View.OnClickListener {

    NotificationManager nm;
    static final int uniqueId = 351351;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbar);
        Button stat = (Button) findViewById(R.id.bStatusBar);
        stat.setOnClickListener(this);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(uniqueId);
    }

    @Override
    public void onClick(View v) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, StatusBar.class), 0);
        String body = "This is a message saying Hi";
        String title = "Notify";
        //Notification n = new Notification(R.drawable.lightning, body, System.currentTimeMillis());
        Notification noti = new Notification.Builder(this)
                .setContentIntent(pi)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.lightning)
                .setWhen(System.currentTimeMillis())
                .build();
        nm.notify(uniqueId, noti);
        finish();
    }
}
