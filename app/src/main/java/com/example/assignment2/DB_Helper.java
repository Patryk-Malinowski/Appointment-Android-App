package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {
    // define the schema
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "appointmentsdb";
    private static final String TABLE_Appointments = "appointmentdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_TIME = "time";
    private static final String KEY_DATE = "date";



    public DB_Helper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB_Helper", "onCreate called");
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Appointments + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TIME + " TEXT," +
                KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Appointments);
        onCreate(db);
    }

    public void insertUserDetails(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvalues = new ContentValues();
        cvalues.put(KEY_TIME, item.getTime());
        cvalues.put(KEY_DATE, item.getDate());
        db.insert(TABLE_Appointments,null,cvalues);
        db.close();
    }


}
