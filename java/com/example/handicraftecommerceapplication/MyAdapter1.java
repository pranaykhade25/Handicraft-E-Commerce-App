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

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

    Context context;
    ArrayList<Pottery_POJO> list;

    public MyAdapter1(Context context, ArrayList<Pottery_POJO> list) {
        this.context = context;
        this.list = list;
    }

    public void setFilteredList(List<Pottery_POJO> filteredList){
        this.list= (ArrayList<Pottery_POJO>) filteredList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item1,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pottery_POJO pot = list.get(position);
        holder.product_name.setText(pot.getProduct_name());
        holder.product_price.setText(pot.getProduct_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView product_name,product_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_name12);
            product_price = itemView.findViewById(R.id.product_price12);
        }
    }
}
