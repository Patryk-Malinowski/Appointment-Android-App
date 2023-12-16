package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ViewAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        ListView appointmentsListView = findViewById(R.id.appointmentsListView);


        // retrieve appointments from the database for the specified date
        DB_Helper dbHelper = new DB_Helper(this);
        List<Item> appointments = dbHelper.getAllAppointments();

        // display the appointments in a ListView
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointments);
        appointmentsListView.setAdapter(adapter);


        // initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationMenu navigationMenu = new NavigationMenu(this, bottomNavigationView);
    }
}