package com.example.Patryk_Malinowski;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


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
            startActivity(new Intent(MainActivity.this, AppointmentsActivity.class));
        });


        // initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationMenu navigationMenu = new NavigationMenu(this, bottomNavigationView);
    }
}






