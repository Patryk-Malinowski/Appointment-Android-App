package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button continueButton;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continueButton = findViewById(R.id.button);
        calendarView = findViewById(R.id.calendarView);

        // get the current date
        long currentDate = System.currentTimeMillis();

        // set the minimum date to today
        calendarView.setMinDate(currentDate);

        // set the maximum date to 2 months ahead
        Calendar twoMonthsAhead = Calendar.getInstance();
        twoMonthsAhead.add(Calendar.MONTH, 2);
        long maxDate = twoMonthsAhead.getTimeInMillis();
        calendarView.setMaxDate(maxDate);


        // calendarView listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

            }
        });






    }


}


