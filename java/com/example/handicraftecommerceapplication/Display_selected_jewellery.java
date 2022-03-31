package com.example.handicraftecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Display_selected_jewellery extends AppCompatActivity {
    ImageView pots;
    TextView txt1;
    TextView txt2;
    Button buynow;
    Button addtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_selected_jewellery);

        buynow =(Button) findViewById(R.id.btn_Buy_now);
        pots =(ImageView) findViewById(R.id.imageView_for_pots);
        txt1 =(TextView) findViewById(R.id.tvName_for_pots);
        txt2 =(TextView) findViewById(R.id.txprice_for_pots);

        Intent intent = getIntent();
        String selectdNames = intent.getStringExtra("name");
        int selectedImage = intent.getIntExtra("image", 0);
        String selectdDes = intent.getStringExtra("description");

        txt1.setText(selectdNames);
        pots.setImageResource(selectedImage);
        txt2.setText(selectdDes);


        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Display_selected_jewellery.this, paymentdetails.class);
                i.putExtra("product_name_price",selectdNames);
                startActivity(i);

            }
        });

    }
}