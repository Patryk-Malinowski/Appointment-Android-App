package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper {
    // define the schema
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "appointmentsdb";
    private static final String TABLE_Appointments = "appointmentdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_TIME = "time";
    private static final String KEY_DATE = "date";
    private final String TAG = "DB_Helper";



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

    // this method retrieves the booked appointment details at a specific date
    public List<Item> getAppointmentsByDate(String searchDate) {
        List<Item> appointmentsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // define columns
        String[] columns = {KEY_TIME, KEY_DATE};

        // define criteria (WHERE clause) (the "?" is a placeholder for a parameter, in this case it will be searchDate)
        String selection = KEY_DATE + " = ?";

        // specify date to search by
        String[] selectionArgs = {searchDate};

        // query db for specific date (searchDate)
        Cursor cursor = db.query(TABLE_Appointments, columns, selection, selectionArgs, null, null, null);

        // check cursor is not null & at least one row in results
        if (cursor != null && cursor.moveToFirst()) {
            int timeIndex = cursor.getColumnIndex(KEY_TIME);
            int dateIndex = cursor.getColumnIndex(KEY_DATE);

            do {
                // get time and date from current row
                String time = cursor.getString(timeIndex);
                String date = cursor.getString(dateIndex);

                // create Item object
                Item appointment = new Item(time, date);

                // add appointment to list
                appointmentsList.add(appointment);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        // return all appointments that match searchDate
        return appointmentsList;

    }


    // this method displays all booked appointments
    public List<Item> getAllAppointments() {
        List<Item> appointmentsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // define columns
        String[] columns = {KEY_TIME, KEY_DATE};

        // query db for specific date (searchDate)
        Cursor cursor = db.query(TABLE_Appointments, columns, null, null, null, null, null);

        // check cursor is not null & at least one row in results
        if (cursor != null && cursor.moveToFirst()) {
            int timeIndex = cursor.getColumnIndex(KEY_TIME);
            int dateIndex = cursor.getColumnIndex(KEY_DATE);

            do {
                // get time and date from current row
                String time = cursor.getString(timeIndex);
                String date = cursor.getString(dateIndex);

                // create Item object
                Item appointment = new Item(time, date);

                // add appointment to list
                appointmentsList.add(appointment);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        // return all appointments that match searchDate
        return appointmentsList;

    }




}
