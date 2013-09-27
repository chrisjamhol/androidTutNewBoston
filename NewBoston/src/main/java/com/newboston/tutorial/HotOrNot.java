package com.newboston.tutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Chris on 26.09.13.
 */
public class HotOrNot {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final String KEY_HOTNESS = "person_hotness";
    private static final String DB_NAME = "HotOrNotDb";
    private static final String DB_TABLE = "personTable";
    private static final int DB_VERSION = 1;
    private DbHelper ourHelper;
    private Context ourContext = null;
    private SQLiteDatabase ourDatabase;
    private String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};


    public HotOrNot(Context c) {
        ourContext = c;
    }

    public HotOrNot open() throws SQLException {
        ourHelper = new DbHelper(ourContext, KEY_NAME, null, DB_VERSION);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createEntry(String name, String hotness) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_HOTNESS, hotness);
        return ourDatabase.insert(DB_TABLE, null, cv);
    }

    public String getData() throws SQLException {
        Cursor c = ourDatabase.query(DB_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iHotness = c.getColumnIndex(KEY_HOTNESS);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iHotness) + "\n";
        }
        return result;
    }

    public String getName(long rowId) throws SQLException {
        Cursor c = ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + rowId, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;
    }

    public String getHotness(long rowId) throws SQLException {
        Cursor c = ourDatabase.query(DB_TABLE, columns, KEY_ROWID + "=" + rowId, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            String hotness = c.getString(2);
            return hotness;
        }
        return null;
    }

    public void updateEntry(long rowId, String mName, String mHotness) throws SQLException {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME, mName);
        cvUpdate.put(KEY_HOTNESS, mHotness);
        ourDatabase.update(DB_TABLE, cvUpdate, KEY_ROWID + "=" + rowId, null);
    }

    public void deleteEntry(long rowId) {
        ourDatabase.delete(DB_TABLE, KEY_ROWID + "=" + rowId, null);
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_HOTNESS + " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

}
