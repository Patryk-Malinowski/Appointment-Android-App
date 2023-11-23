package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bookAppointmentButton = findViewById(R.id.bookAppointmentButton);
        Button viewAppointmentsButton = findViewById(R.id.viewAppointmentsButton);

        bookAppointmentButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, BookAppointmentActivity.class));
        });

        viewAppointmentsButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ViewAppointmentActivity.class));
        });
    }
}






