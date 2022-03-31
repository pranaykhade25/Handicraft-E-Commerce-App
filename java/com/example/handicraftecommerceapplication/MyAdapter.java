package com.example.handicraftecommerceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Customer_POJO> list;

    public MyAdapter(Context context, ArrayList<Customer_POJO> list) {
        this.context = context;
        this.list = list;
    }
    public void setFilteredList(List<Customer_POJO> filteredList){
        this.list= (ArrayList<Customer_POJO>) filteredList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Customer_POJO cust = list.get(position);
        holder.full_name.setText(cust.getFull_name());
        holder.email.setText(cust.getEmail());
        holder.password.setText(cust.getPassword());
        holder.gender.setText(cust.getGender());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView full_name,email,password,gender;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            full_name = itemView.findViewById(R.id.full_name);
            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.password);
            gender = itemView.findViewById(R.id.gender);

        }
    }
}
