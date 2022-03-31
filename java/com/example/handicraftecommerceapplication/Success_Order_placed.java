package com.example.handicraftecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Success_Order_placed extends AppCompatActivity {

    Button btn_go_back_home;
    ImageView image_tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_order_placed);

        btn_go_back_home = (Button) findViewById(R.id.back_to_categories);
        image_tick = (ImageView) findViewById(R.id.imageView2);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_animation);
        image_tick.startAnimation(animation);

        btn_go_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Success_Order_placed.this, Category.class);
                startActivity(i);
            }
        });
    }
}