package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ViewAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        ListView appointmentsListView = findViewById(R.id.appointmentsListView);

        // Replace "2023-12-01" with the actual date you want to search for
        String searchDate = "03/12/2023";

        // Retrieve appointments from the database for the specified date
        DB_Helper dbHelper = new DB_Helper(this);
        List<Item> appointments = dbHelper.getAppointmentsByDate(searchDate);

        // Display the appointments in a ListView
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointments);
        appointmentsListView.setAdapter(adapter);
    }
}