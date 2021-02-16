package com.example.drawer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "TerminPlaner";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Termin";
    private static final String COL_id = "id";
    private static final String COL_name = "name";
    private static final String COL_time = "time";
    private static final String COL_type = "type";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_name + " TEXT NOT NULL, " +
                    COL_time + " TEXT, " +
                    COL_type + " TEXT, " ;
    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public List<termin> getAllSpots() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_id, COL_name, COL_time, COL_type
        };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
                null);
        List<termin> spotList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String web = cursor.getString(2);
            String phone = cursor.getString(3);
            String address = cursor.getString(4);
            byte[] image = cursor.getBlob(5);
            Spot spot = new Spot(id, name, web, phone, address, image);
            spotList.add(spot);
        }
        cursor.close();
        return spotList;
    }

    public termin findById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                COL_name, COL_time, COL_type
        };
        String selection = COL_id + " = ?;";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        termin termin = null;
        if (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String web = cursor.getString(1);
            String phone = cursor.getString(2);
            String address = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            termin = new termin(id, name, web, phone, address, image);
        }
        cursor.close();
        return spot;
    }
}
