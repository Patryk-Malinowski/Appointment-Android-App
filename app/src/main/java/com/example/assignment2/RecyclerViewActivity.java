package com.example.assignment2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView1);
        dateTextView = findViewById(R.id.dateTextView);

        // Retrieve the selected date from the intent
        long selectedDate = getIntent().getLongExtra("selectedDate", 0);

        // Use the selectedDate as needed, for example, display it in a TextView
        dateTextView.setText("Selected Date: " + selectedDate);

        // Set up your RecyclerView as needed
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
}
