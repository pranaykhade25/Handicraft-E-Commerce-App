package com.example.handicraftecommerceapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class pottery extends AppCompatActivity {
    GridView potteryGV;
    String[] names = {"Blue Plates\nPrice: 599.0 Rs", "Blue Pottery Vase\nPrice: 799.0 Rs", "Enamel Pottery\n Price: 499.0 Rs", "Curve Pots\n Price: 999.0 Rs", "Rajashtani Special\n Price: 1999.0 Rs", "Yellow Pottery Vase\n Price: 1599.0 Rs", "Neerja Pottery\n Price: 599.0 Rs", "Special Design Pots\n Price: 1599.0 Rs"};
    int[] images = {R.drawable.blueplates, R.drawable.bluepotteryvase, R.drawable.enamelpottery, R.drawable.curvepots, R.drawable.pottery, R.drawable.yellowpotteryvase, R.drawable.neerjapottery, R.drawable.specialdesignpots};
    String[] description = {"Color: Black & White\n" +
            "Material: Pottery\n" +
            "Traditionally handcrafted and hand painted pottery art work done by expert craftsman.", "Material - Quartz Powder, Glass Powder, Katira \n  Blue Pottery Products are suitable for storing dry things only ", "MATERIAL : Ceramic, Pre-attached wooden wall mount \n Aesthetically designed platter with textured detailing is that one piece that draws all attention. We have used superior quality ceramic plate",
            "Finishing :\tHAND MADE\n" +
                    "Feature : \tEco-Friendly \n Capacity : \t14 LTR \n Clay matka ",
            "Materials : quartz, raw glaze, sodium sulphate, and multani mitti \n Blue pottery is beautifully decorated with the brush when the pot is rotated ",
            "Material: Ceramic \n Blue Pottery Vases & Pots /Handmade & Hand Decorated Ceramic Pots & Vases/ Yellow Floral Design Flower Vases & Pots",
            "Material: Quartz Powder, Glass Powder,  Oxide Colors \n Blue Pottery Handmade Jar",
            "Shape: Round Shaped \n Finish : \tPolished \n Clay Modern Decorative Flower Pot"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pottery_main);

        potteryGV = findViewById(R.id.idGVcourses);

        pottery.CustomAdapter customAdapter = new pottery.CustomAdapter(names, images, description, this);
        potteryGV.setAdapter(customAdapter);

        potteryGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName = names[i];
                int selectImages = images[i];
                String selectedDes = description[i];


                startActivity(new Intent(pottery.this, Display_selected_pottery.class).putExtra("name", selectedName).putExtra("image", selectImages).putExtra("description", selectedDes));
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
                view=layoutInflater.inflate(R.layout.activity_pottery,viewGroup,false);
            }

            TextView tvName=view.findViewById(R.id.idTVCourse);
            ImageView imageview=view.findViewById(R.id.idIVcourse);

            tvName.setText(imageNames[i]);
            imageview.setImageResource(imagesPhoto[i]);

            return view;
        }
    }
}