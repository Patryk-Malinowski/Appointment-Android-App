package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private String selectedDate;

    // constructor
    public MyRecyclerViewAdapter(Context context, List<String> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setSelectedDate(String date) {
        this.selectedDate = date;
        notifyDataSetChanged();
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);

    }



    void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }




    // inflate the rows
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_layout, parent, false);

        return new ViewHolder(view);
    }

    // load or set the data into the views
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String time = mData.get(position);
        holder.appointmentTimeTextView.setText(time);

    }


    public String getItem(int id){
        return mData.get(id);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView appointmentAvailabilityTextView, appointmentTimeTextView;
        Button bookButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentAvailabilityTextView = itemView.findViewById(R.id.availabilityTextView);
            appointmentTimeTextView = itemView.findViewById(R.id.timeTextView);
            bookButton = itemView.findViewById(R.id.bookButton);

            bookButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());

            String time = appointmentAvailabilityTextView.getText().toString();
            Item item = new Item(time, selectedDate);

            DB_Helper dbHelper = new DB_Helper(itemView.getContext());
            dbHelper.insertUserDetails(item);

        }
    }















}
