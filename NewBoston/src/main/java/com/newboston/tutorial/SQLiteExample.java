package com.newboston.tutorial;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by Chris on 26.09.13.
 */
public class SQLiteExample extends Activity implements View.OnClickListener {

    Button sqlUpdate, sqlView, sqlGetInfo, sqlModify, sqlDelete;
    EditText sqlName, sqlHotness, sqlRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);
        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlHotness = (EditText) findViewById(R.id.etSQLHotness);

        sqlView = (Button) findViewById(R.id.bSQLopenView);
        sqlView.setOnClickListener(this);
        sqlUpdate.setOnClickListener(this);

        sqlRow = (EditText) findViewById(R.id.etRowId);
        sqlModify = (Button) findViewById(R.id.bEditEntry);
        sqlModify.setOnClickListener(this);
        sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
        sqlGetInfo.setOnClickListener(this);
        sqlDelete = (Button) findViewById(R.id.bDeleteEntry);
        sqlDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSQLUpdate:
                boolean worked = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();

                    HotOrNot entry = new HotOrNot(this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                } catch (Exception e) {
                    worked = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Error");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (worked) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Dialog title");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }

                break;
            case R.id.bSQLopenView:
                Intent i = new Intent("com.newboston.tutorial.SQLVIEW");
                startActivity(i);
                break;

            case R.id.bGetInfo:
                getInfo();
                break;
            case R.id.bEditEntry:
                editEntry();
                break;
            case R.id.bDeleteEntry:
                deleteEntry();
                break;
        }
    }

    private void deleteEntry() {
        long rowId = Long.parseLong(sqlRow.getText().toString());
        HotOrNot hon = new HotOrNot(this);
        try {
            hon.open();
            hon.deleteEntry(rowId);
            hon.close();
        } catch (SQLException e) {
            Dialog d = new Dialog(this);
            d.setTitle("Error");
            TextView tv = new TextView(this);
            tv.setText(e.toString());
            d.setContentView(tv);
            d.show();
        } catch (Exception e) {
            Dialog d = new Dialog(this);
            d.setTitle("Error");
            TextView tv = new TextView(this);
            tv.setText(e.toString());
            d.setContentView(tv);
            d.show();
        }
    }

    private void editEntry() {
        long rowId = Long.parseLong(sqlRow.getText().toString());
        String mName = sqlName.getText().toString();
        String mHotness = sqlHotness.getText().toString();
        HotOrNot hon = new HotOrNot(this);
        try {
            hon.open();
            hon.updateEntry(rowId, mName, mHotness);
            hon.close();
        } catch (SQLException e) {
            Dialog d = new Dialog(this);
            d.setTitle("Error");
            TextView tv = new TextView(this);
            tv.setText(e.toString());
            d.setContentView(tv);
            d.show();
        } catch (Exception e) {
            Dialog d = new Dialog(this);
            d.setTitle("Error");
            TextView tv = new TextView(this);
            tv.setText(e.toString());
            d.setContentView(tv);
            d.show();
        }
    }

    private void getInfo() {
        long rowId = Long.parseLong(sqlRow.getText().toString());
        HotOrNot hon = new HotOrNot(this);
        try {
            hon.open();
            String returnedName = hon.getName(rowId);
            String returnedHotness = hon.getHotness(rowId);
            hon.close();
            sqlName.setText(returnedName);
            sqlHotness.setText(returnedHotness);
        } catch (SQLException e) {
            Dialog d = new Dialog(this);
            d.setTitle("Error");
            TextView tv = new TextView(this);
            tv.setText(e.toString());
            d.setContentView(tv);
            d.show();
        } catch (Exception e) {
            Dialog d = new Dialog(this);
            d.setTitle("Error");
            TextView tv = new TextView(this);
            tv.setText(e.toString());
            d.setContentView(tv);
            d.show();
        }
    }
}
