package com.example.Patryk_Malinowski;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ViewAppointmentsByDateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments_by_date);

        ListView appointmentsListView = findViewById(R.id.appointmentsListView);

        EditText date = findViewById(R.id.editTextDate);

        Button searchButton = findViewById(R.id.buttonSearch);

        searchButton.setOnClickListener(v -> {
            String searchDate = date.getText().toString();

            // retrieve appointments from the database for the specified date
            DB_Helper dbHelper = new DB_Helper(this);
            List<Item> appointments = dbHelper.getAppointmentsByDate(searchDate);

            // display the appointments in a ListView
            ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointments);
            appointmentsListView.setAdapter(adapter);

        });


        // initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationMenu navigationMenu = new NavigationMenu(this, bottomNavigationView);
    }
}