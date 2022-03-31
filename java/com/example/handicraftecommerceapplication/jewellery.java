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

public class jewellery extends AppCompatActivity {

    GridView potteryGV;
    String[] names = {"Bridal jewellery set\nPrice:1000.0 Rs","Rajasthani Payal\nPrice: 350.0 Rs","Traditional Necklace\nPrice: 500.0 Rs","Rajasthani Bangles\nPrice 600.0 Rs","Royal set\nPrice:400.0 Rs","Rajasthani Earrings\nPrice: 300.0 Rs","Rajasthani Mangtika\nPrice:250.0 Rs","Choker Necklace Set\nPrice: 900.0 Rs"};
    int[] images = {R.drawable.jewellery1, R.drawable.jewellery2,R.drawable.jewellery3,R.drawable.jewellery4,R.drawable.jewellery5, R.drawable.jewellery6, R.drawable.jewellery7,R.drawable.jewellery8};
    String[] description = {"Category: Jadau Jewellery \n This Rajasthani jewellery set is made with white metal base and has real gold plating on it to make it look like original gold jewellery.\n" ,
            "Party Oxidised silver Anklets ", "This rajputi aad necklace can make for a great pick to team up with your bridal lehenga. We love how those striking floral motifs are adding an edge to this beautiful necklace.",
            "Handmade Rajasthani Bangles Pearls & Ribbon Design Rajputi Chuda Set for Girls/Women ","Silvermerc Designs green & White color moti layered Rajputi aad Indian Rajasthani With American diamond premium quality ethnic necklace",
            "Indian traditional style fabulous green color stone handmade earring ",
            "Gold-Plated Off-White & Red Kundan-Studded & Pearl Beaded Maang Tikka",
            "RAJASTHANI Golden and Red Janjeer Kundan Choker Necklace Set for Women"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jewellery_main);

        potteryGV=findViewById(R.id.idGVcourses);

        CustomAdapter customAdapter=new CustomAdapter(names,images,description,this);

        potteryGV.setAdapter(customAdapter);

        potteryGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName=names[i];
                int selectImages=images[i];
                String selectedDes=description[i];


                startActivity(new Intent(jewellery.this,Display_selected_jewellery.class).putExtra("name",selectedName).putExtra("image",selectImages).putExtra("description",selectedDes));
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
                view=layoutInflater.inflate(R.layout.activity_jewellery,viewGroup,false);
            }

            TextView tvName=view.findViewById(R.id.idTVCourse);
            ImageView imageview=view.findViewById(R.id.idIVcourse);

            tvName.setText(imageNames[i]);
            imageview.setImageResource(imagesPhoto[i]);

            return view;
        }
    }
}