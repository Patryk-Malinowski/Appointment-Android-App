package com.example.Patryk_Malinowski;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {
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

                demo_adapter.updateData(availableTimeSlots);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });



        // initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationMenu navigationMenu = new NavigationMenu(this, bottomNavigationView);
    }


    // method to add 14 tabs into tab layout (dates start from today)
    private void addTabs() {
        long today = MaterialDatePicker.todayInUtcMilliseconds(); // today in milliseconds
        long oneDay = (24 * 60 * 60 * 1000); // one day in milliseconds
        for (int i = 0; i < 14; i++) { // make 14 tabs

            tabLayout.addTab(tabLayout.newTab().setText(convertDate(today)));
            today += oneDay; // gets set to next day
        }
    }

    // method to convert the date from milliseconds to dd/mm/yyyy
    private String convertDate(long selectedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date(selectedDate));
    }

    // method to calculate selected date
    private long calculateSelectedDate(int position) {
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        // calculate the selected date based on the position (in days)
        long selectedDate = today + (position * 24 * 60 * 60 * 1000);
        return selectedDate;
    }

    // method to check time slot availability on a given date
    public boolean isTimeSlotAvailable(String date, String time) {
        // check if the time slot is available for the selected date
        return new DB_Helper(this).checkAppointmentAvailability(date, time);
    }
}
