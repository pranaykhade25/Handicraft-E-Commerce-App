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

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    Context context;
    ArrayList<Order_POJO> list;

    public MyAdapter2(Context context, ArrayList<Order_POJO> list) {
        this.context = context;
        this.list = list;
    }
    public void setFilteredList(List<Order_POJO> filteredList){
        this.list= (ArrayList<Order_POJO>) filteredList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Order_POJO order = list.get(position);
        holder.Buildingname.setText(order.getBuildingname());
        holder.City.setText(order.getCity());
        holder.Email.setText(order.getEmail());
        holder.Fullname.setText(order.getFullname());
        holder.Ordered_Product.setText(order.getOrdered_Product());
        holder.Payment_Mode.setText(order.getPayment_Mode());
        holder.Phonenumber.setText(order.getPhonenumber());
        holder.Pincode.setText(order.getPincode());
        holder.Roadname.setText(order.getRoadname());
        holder.State.setText(order.getState());
        holder.Size.setText(order.getSize());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Buildingname,City,Email,Fullname,Ordered_Product,Password,Payment_Mode,Phonenumber,Pincode,Roadname,Size,State;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Buildingname = itemView.findViewById(R.id.Buildingname);
            City = itemView.findViewById(R.id.City);
            Email = itemView.findViewById(R.id.Email);
            Fullname = itemView.findViewById(R.id.Fullname);
            Ordered_Product = itemView.findViewById(R.id.Ordered_Product);
            Payment_Mode = itemView.findViewById(R.id.Payment_Mode);
            Phonenumber = itemView.findViewById(R.id.Phonenumber);
            Pincode = itemView.findViewById(R.id.Pincode);
            Roadname = itemView.findViewById(R.id.Roadname);
            State = itemView.findViewById(R.id.state);
            Size = itemView.findViewById(R.id.Size);

        }
    }
}
