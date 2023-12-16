package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppointmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        Button viewAppointmentsButton = findViewById(R.id.viewAppointmentsButton);
        Button viewAppointmentsByDateButton = findViewById(R.id.viewAppointmentsByDateButton);

        viewAppointmentsButton.setOnClickListener(v -> {
            startActivity(new Intent(AppointmentsActivity.this, ViewAppointmentActivity.class));
        });

        viewAppointmentsByDateButton.setOnClickListener(v -> {
            startActivity(new Intent(AppointmentsActivity.this, ViewAppointmentsByDateActivity.class));
        });


        // initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationMenu navigationMenu = new NavigationMenu(this, bottomNavigationView);
    }

}