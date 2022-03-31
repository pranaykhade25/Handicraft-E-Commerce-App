package com.example.handicraftecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Admin_Categories extends AppCompatActivity {

    ImageButton customers12,payments12;
    Spinner category;
    TextView registered_users,orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_categories);

        customers12 = (ImageButton) findViewById(R.id.customers);
        payments12 = (ImageButton) findViewById(R.id.payments);
        category = (Spinner) findViewById(R.id.categories);
        registered_users = (TextView) findViewById(R.id.users_textview);
        orders = (TextView)findViewById(R.id.order_placed);

        customers12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Admin_Categories.this,Customer_List.class);
                startActivity(i);
            }
        });

        registered_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Admin_Categories.this,Customer_List.class);
                startActivity(i);
            }
        });

        payments12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin_Categories.this,Display_Received_Orders.class);
                startActivity(i);
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin_Categories.this,Display_Received_Orders.class);
                startActivity(i);
            }
        });


        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = category.getSelectedItem().toString();
                if(str.equals("Pottery"))
                {
                    Intent i = new Intent(Admin_Categories.this,Pottery_List.class);
                    startActivity(i);
                }
                else if(str.equals("Furniture"))
                {
                    Intent i = new Intent(Admin_Categories.this,Furniture_List.class);
                    startActivity(i);
                }
                else if(str.equals("Clothing"))
                {
                    Intent i = new Intent(Admin_Categories.this,Clothing_List.class);
                    startActivity(i);
                }
                else if(str.equals("Jewellery"))
                {
                    Intent i = new Intent(Admin_Categories.this,Jewellery_List.class);
                    startActivity(i);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}