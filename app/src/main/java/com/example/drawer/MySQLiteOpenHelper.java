package com.example.drawer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "TerminPlan";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Termin";
    private static final String COL_id = "id";
    private static final String COL_name = "name";
    private static final String COL_time = "time";
    private static final String COL_type = "type";
    private static final String COL_note= "note";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_name + " TEXT NOT NULL, " +
                    COL_time + " TEXT, " +
                    COL_type + " TEXT, " +
                    COL_note + " TEXT )" ;

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        setDefaultLabel(db);
    }
    public void setDefaultLabel(SQLiteDatabase db) {
        // create default label
        ContentValues values = new ContentValues();
        values.put(COL_name, "refrt1");
        values.put(COL_time, "15min");
        values.put(COL_type, "15min");
        values.put(COL_note, "");
        db.insert(TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(COL_name, "refrt2");
        values.put(COL_time, "30min");
        values.put(COL_type, "30min");
        values.put(COL_note, "");
        db.insert(TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(COL_name, "refrt3");
        values.put(COL_time, "60min");
        values.put(COL_type, "60min");
        values.put(COL_note, "");
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(termin termin) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_name, termin.getName());
        values.put(COL_time, termin.getTime());
        values.put(COL_type, termin.getType());
        values.put(COL_note, termin.getNote());
        return db.insert(TABLE_NAME, null, values);
    }

    public List<termin> getAllSpots() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_id, COL_name, COL_time, COL_type,COL_note
        };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
                null);
        List<termin> terminList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String time = cursor.getString(2);
            String type = cursor.getString(3);
            String note = cursor.getString(4);
            termin termin = new termin(id, name, time, type, note);
            terminList.add(termin);
        }
        cursor.close();
        return terminList;
    }

    public termin findById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                COL_name, COL_time, COL_type,COL_note
        };
        String selection = COL_id + " = ?;";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        termin termin = null;
        if (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String time = cursor.getString(1);
            String type = cursor.getString(2);
            String note = cursor.getString(3);
            termin = new termin(id, name, time, type, note);
        }
        cursor.close();
        return termin;
    }
}
