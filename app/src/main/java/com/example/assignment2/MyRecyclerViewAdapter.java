package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // constructor
    public MyRecyclerViewAdapter(Context context, List<String> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
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
        String name = mData.get(position);
        holder.myTextView.setText(name);

    }


    public String getItem(int id){
        return mData.get(id);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.row_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());



        }
    }















}
