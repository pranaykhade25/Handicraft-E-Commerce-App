package com.example.handicraftecommerceapplication;


import static android.content.Context.LAYOUT_INFLATER_SERVICE;

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
import java.util.ArrayList;

public class    furniture extends AppCompatActivity {

    GridView potteryGV;
    String[] names = {"Chair\nPrice:400.0 Rs"," small Bed \nPrice: 490.0 Rs","Chair\nPrice: 500.0 Rs","small table with chairs  \nPrice: 1000.0 Rs","Rajasthani Sofa\nPrice 1250.0 Rs","Designed sofa\nPrice:1500.0 Rs"," Bed  \nPrice: 1200.0 Rs","Maharajasofa\nPrice:1450.0 Rs"};
    int[] images = {R.drawable.chair, R.drawable.bed,R.drawable.chairtable,R.drawable.furniture,R.drawable.sofa, R.drawable.sofa2, R.drawable.posterbed,R.drawable.maharajasofa};
    String[] description = {"Royal Rajasthani Rajwadi Folding Wooden Chair with Jute Seat Low Seating ", "Pretty print, excellent fabric, extremely soft to touch and ability to machine wash makes this bed sheet a must-have in the bed linen collection for the season. ", "A very exquisite and traditional bajot set especially hand painted and crafted, made of high-quality wood giving it a very unique and traditional Rajasthani look ", "Wooden Twist Wooden Handicraft Couch for Living Room in Stylish Look Chaise Lounge  ","Vintage Sheesham Wood Handcrafted Deewan Chaise Lounge","Bring in a vintage charm to your room with this premium wood four-poster bed, with spiral-turned reeded faceted uprights, joined by bed rails","We are acquainted with different design patterns prevailing in varied regions to manufactured and supplied an exclusive Designer Furniture ","Metallic Gold Color Structure Decorated With Wooden Hand Carved Motifs Work On Grade 1 Teak Wood Colored With Metallic Silver Color Giving This Masterpiece a Majestic Look! Designed & Crafted By Experts Of Rajshthani Handicrafts."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.furniture_main);

        potteryGV=findViewById(R.id.idGVcourses);

        CustomAdapter customAdapter=new CustomAdapter(names,images,description,this);

        potteryGV.setAdapter(customAdapter);

        potteryGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName=names[i];
                int selectImages=images[i];
                String selectedDes=description[i];


                startActivity(new Intent(furniture.this,Display_selected_furniture.class).putExtra("name",selectedName).putExtra("image",selectImages).putExtra("description",selectedDes));
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
                view=layoutInflater.inflate(R.layout.activity_furniture,viewGroup,false);
            }


            TextView tvName=view.findViewById(R.id.idTVCourse);

            ImageView imageview=view.findViewById(R.id.idIVcourse);


            tvName.setText(imageNames[i]);
            imageview.setImageResource(imagesPhoto[i]);

            return view;
        }
    }
}