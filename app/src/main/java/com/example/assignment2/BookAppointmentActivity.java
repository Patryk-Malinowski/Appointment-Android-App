// Patryk Malinowski
// R00210173
// Assignment 2

package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {
    private final String TAG = "BookAppointmentActivity";
    private TabLayout tabLayout;
    private MyRecyclerViewAdapter demo_adapter;
    private String selectedDate = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tabLayout = findViewById(R.id.tabLayout);

        RecyclerView rv = findViewById(R.id.recyclerView1);

        ArrayList<String> myData = new ArrayList<>();
        myData.add("9:00");
        myData.add("10:00");
        myData.add("11:00");
        myData.add("12:00");
        myData.add("13:00");
        myData.add("14:00");
        myData.add("15:00");
        myData.add("16:00");

        // Create a new list to store available time slots
        ArrayList<String> availableTimeSlots = new ArrayList<>();

        rv.setLayoutManager(new LinearLayoutManager(this));
        demo_adapter = new MyRecyclerViewAdapter(this, availableTimeSlots);

        selectedDate = convertDate(MaterialDatePicker.todayInUtcMilliseconds());
        demo_adapter.setSelectedDate(selectedDate);

        rv.setAdapter(demo_adapter);

        addTabs();


        // iterate over the predefined time slots
        for (String time : myData) {
            if (isTimeSlotAvailable(selectedDate, time)) {
                availableTimeSlots.add(time);
            }
        }



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedDate = convertDate(calculateSelectedDate(tab.getPosition()));
                demo_adapter.setSelectedDate(selectedDate);

                // clear the availableTimeSlots list before the for loop
                availableTimeSlots.clear();

                // iterate over the predefined time slots
                for (String time : myData) {
                    if (isTimeSlotAvailable(selectedDate, time)) {
                        availableTimeSlots.add(time);
                    }
                }

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
        long today = MaterialDatePicker.todayInUtcMilliseconds(); // today in milliseconds
        long oneDay = (24 * 60 * 60 * 1000); // one day in milliseconds
        for (int i = 0; i < 14; i++) { // make 14 tabs

            tabLayout.addTab(tabLayout.newTab().setText(convertDate(today)));
            today += oneDay; // gets set to next day
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

    // Method to check time slot availability on a given date
    private boolean isTimeSlotAvailable(String date, String time) {
        // Use the checkAppointmentAvailability method from DB_Helper
        // to check if the time slot is available for the selected date
        return new DB_Helper(this).checkAppointmentAvailability(date, time);
    }
}
