// Patryk Malinowski
// R00210173
// Assignment 2

package com.example.assignment2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {
    private final String TAG = "BookAppointmentActivity";
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tabLayout = findViewById(R.id.tabLayout);

        addTabs();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                showDataForSelectedDate(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    // Method to display data for the selected date in tab layout
    private void showDataForSelectedDate(int position) {
        long selectedDate = calculateSelectedDate(position);

        // Log the selected date
        Log.d(TAG, convertDate(selectedDate));
    }

    // Method to add 14 tabs into tab layout (dates start from today)
    private void addTabs() {
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        long oneDay = (24 * 60 * 60 * 1000);
        for (int i = 0; i < 14; i++) {

            tabLayout.addTab(tabLayout.newTab().setText(convertDate(today)));
            today += oneDay;
        }
    }

    // Method to convert the date from milliseconds to dd/mm/yyyy
    private String convertDate(long selectedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date(selectedDate));
    }

    // Method to calculate selected date
    private long calculateSelectedDate(int position) {
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        // Calculate the selected date based on the position (in days)
        long selectedDate = today + (position * 24 * 60 * 60 * 1000);
        return selectedDate;
    }



}
