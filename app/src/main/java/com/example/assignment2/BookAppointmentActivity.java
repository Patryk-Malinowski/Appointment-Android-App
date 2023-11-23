package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {
    private final String TAG = "BookAppointmentActivity";
    private TextView selectedDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        selectedDateTextView = findViewById(R.id.selectedDateTextView);
        Button pickDateButton = findViewById(R.id.pickDateButton);

        pickDateButton.setOnClickListener(v -> showDatePicker());
    }

    private void showDatePicker() {
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        long fourteenDays = today + (14 * 24 * 60 * 60 * 1000);


        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointBackward.before(fourteenDays));



        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(today) // Default selection is today
                .setCalendarConstraints(constraintsBuilder.build())
                .build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selectedDate) {
                if (selectedDate < today) {
                    Toast.makeText(BookAppointmentActivity.this, "Date is in the past", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Date is in the past.");
                }
                else {
                    displaySelectedDate(selectedDate);
                }
            }
        });

        datePicker.show(getSupportFragmentManager(), datePicker.toString());
    }

    private void displaySelectedDate(Long selectedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = sdf.format(new Date(selectedDate));

        selectedDateTextView.setText("Selected Date: " + formattedDate);
    }


}
