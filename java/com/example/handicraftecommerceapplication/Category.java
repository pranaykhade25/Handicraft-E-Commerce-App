package com.example.handicraftecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Category extends AppCompatActivity {

    TextView pottery,jewellery,clothing,furniture,cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        pottery = (TextView) findViewById(R.id.pottery);
        jewellery = (TextView) findViewById(R.id.jewellery);
        clothing = (TextView) findViewById(R.id.clothing);
        furniture = (TextView) findViewById(R.id.furniture);
        cat = (TextView) findViewById(R.id.click_category_to_go_to_login);

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Category.this,Home.class);
                startActivity(i);
            }
        });

        pottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Category.this,pottery.class);
                startActivity(i);
            }
        });

        jewellery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Category.this,jewellery.class);
                startActivity(i);
            }
        });

        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Category.this,furniture.class);
                startActivity(i);
            }
        });

        clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Category.this,clothing.class);
                startActivity(i);
            }
        });

    }
}