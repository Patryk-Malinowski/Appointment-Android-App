package com.example.assignment2;

import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class NavigationMenu implements BottomNavigationView.OnNavigationItemSelectedListener{

    private final AppCompatActivity activity;

    public NavigationMenu(AppCompatActivity activity, BottomNavigationView bottomNavigationView) {
        this.activity = activity;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.navigation_home) {
            activity.startActivity(new Intent(activity, MainActivity.class));
            return true;
        } else if (itemId == R.id.navigation_dashboard) {
            activity.startActivity(new Intent(activity, BookAppointmentActivity.class));
            return true;
        } else if (itemId == R.id.navigation_notifications) {
            activity.startActivity(new Intent(activity, ViewAppointmentActivity.class));
            return true;
        } else {
            return false;
        }
    }


}
