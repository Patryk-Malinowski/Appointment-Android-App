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

        // retrieve the selected date from the intent
        long selectedDate = getIntent().getLongExtra("selectedDate", 0);

        // set date text to selected date
        dateTextView.setText("Selected Date: " + selectedDate);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
}
