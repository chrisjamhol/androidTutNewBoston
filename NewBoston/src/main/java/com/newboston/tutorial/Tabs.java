package com.newboston.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Chris on 19.09.13.
 */
public class Tabs extends Activity implements View.OnClickListener {
    TabHost th;
    int tabCount = 1;
    TextView showResults;
    long start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
       th = (TabHost) findViewById(R.id.tabhost);
        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button bStart = (Button) findViewById(R.id.bStartWatch);
        Button bStop = (Button) findViewById(R.id.bStopWatch);
        showResults = (TextView) findViewById(R.id.tvShowResults);

        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
        newTab.setOnClickListener(this);

        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Stopwatch");
        th.addTab(specs);
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);
        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a tab");
        th.addTab(specs);
        start = 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bAddTab:
                addTabClicked();
                break;
            case R.id.bStartWatch:
                startWatchClicked();
                break;
            case R.id.bStopWatch:
                stopWatchClicked();
                break;
        }
    }

    private void stopWatchClicked() {
        stop = System.currentTimeMillis();
        if(start != 0){
            long restult = stop - start;
            int millis = (int)restult;
            int seconds = (int) restult/1000;
            int minutes = seconds/60;
            millis = millis % 100;
            seconds = seconds % 60;
            showResults.setText(String.format("%d:%02d:%02d",minutes,seconds,millis));
            start = 0;
            stop = 0;
        }
    }

    private void startWatchClicked() {
        start = System.currentTimeMillis();

    }

    private void addTabClicked() {
        TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
        ourSpec.setContent(new TabHost.TabContentFactory(){

            @Override
            public View createTabContent(String tag) {
                TextView text = new TextView(Tabs.this);
                text.setText("You've created a new tab!");
                return (text);
            }
        });
        ourSpec.setIndicator("new Tab "+tabCount);
        th.addTab(ourSpec);
        tabCount++;
    }
}
