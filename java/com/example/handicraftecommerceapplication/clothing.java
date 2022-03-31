package com.example.handicraftecommerceapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class clothing extends AppCompatActivity {

    GridView potteryGV;
    String[] names = {"Rajasthani Boys Ethnic wear\nPrice:4000.0 Rs","Rajasthani Brides Dress\nPrice: 6350.0 Rs","Rajasathani couple dress \nPrice: 5500.0 Rs","Rajasthani girls chaniya choli\nPrice 5600.0 Rs","Rajasthani Grooms Dress\nPrice:6400.0 Rs","Rajasthani Ladies bag \nPrice: 1300.0 Rs","Rajasthani Mojari\nPrice:1250.0 Rs"," Rajasthani Pagdi\nPrice: 900.0 Rs"};
    int[] images = {R.drawable.rajasthani_boys_ethnic_wear, R.drawable.rajasthani_brides_dress,R.drawable.rajasthani_couple_dress,R.drawable.rajasthani_girls_chaniya_choli,R.drawable.rajasthani_grooms_dress, R.drawable.rajasthani_ladies_bag, R.drawable.rajasthani_mojari,R.drawable.rajasthani_pagdi};
    String[] description = {"The dhoti is a long piece of cloth tied around the waist and wrapped around like a loin-cloth between the legs ", " The Lehenga is embellished with coloured semi-precious or precious stones like beads and amber.  ", "special Jumka ", "Choli is the upper part of the attire that usually is of the same color or contrasting one.","The traditional outfit of the Rajasthani groom consists of a long coat called Achkan .","A structured handbag ideal for working women to carry necessary items along with gadgets","These highly colorful Mojaries will undoubtedly suit the fashion taste of every individual.","Turbans worn in Rajasthan are referred to as the pagari. They vary in style, colour and size"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothing_main);

        potteryGV=findViewById(R.id.idGVcourses);

        CustomAdapter customAdapter=new CustomAdapter(names,images,description,this);

        potteryGV.setAdapter(customAdapter);

        potteryGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName=names[i];
                int selectImages=images[i];
                String selectedDes=description[i];


                startActivity(new Intent(clothing.this,Display_selected_clothing.class).putExtra("name",selectedName).putExtra("image",selectImages).putExtra("description",selectedDes));
            }
        });
    }

    public  class CustomAdapter extends BaseAdapter {

        private String[] imageNames;
        private int[] imagesPhoto;
        private String[] imageDes;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] imageNames, int[] imagesPhoto,String[] imageDes,Context context) {

            this.imageNames = imageNames;
            this.imagesPhoto = imagesPhoto;
            this.imageDes=imageDes;
            this.context = context;
            this.layoutInflater=(LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return imagesPhoto.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if(view==null){
                view=layoutInflater.inflate(R.layout.activity_clothing,viewGroup,false);
            }


            TextView tvName=view.findViewById(R.id.idTVCourse);

            ImageView imageview=view.findViewById(R.id.idIVcourse);


            tvName.setText(imageNames[i]);
            imageview.setImageResource(imagesPhoto[i]);

            return view;
        }
    }
}