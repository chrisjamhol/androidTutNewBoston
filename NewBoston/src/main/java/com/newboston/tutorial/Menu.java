package com.newboston.tutorial;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Chris on 12.09.13.
 */
public class Menu extends ListActivity{

    String classes[] = {"MainActivity", "TextPlay", "Email", "Camera", "Data", "GFX",
                        "GFXSurface", "SoundStuff","Tabs","SimpleBrowser","Flipper",
            "SharedPrefs", "InternalData", "ExternalData", "SQLiteExample"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try{
            startActivity(new Intent(Menu.this,Class.forName("com.newboston.tutorial."+classes[position])));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.aboutUs:
                startActivity(new Intent("com.newboston.tutorial.ABOUT"));
                break;
            case R.id.prefrences:
                startActivity(new Intent("com.newboston.tutorial.PREFS"));
                break;
            case R.id.exit:
                finish();
                break;
        }
        return false;
    }
}
