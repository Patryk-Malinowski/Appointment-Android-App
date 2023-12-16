package com.example.Patryk_Malinowski;

public class Item {
    String time, date;

    public Item(String time, String date) {
        this.time = time;
        this.date = date;
    }
    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Time: " + time;
    }

}
