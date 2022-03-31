package com.example.handicraftecommerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Clothing_List extends AppCompatActivity {

    RecyclerView recyclerView;
    private SearchView searchView;
    DatabaseReference database;
    MyAdapter1 myAdapter;
    ArrayList<Pottery_POJO> list_of_pots;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_list);

        recyclerView = findViewById(R.id.pottery_list12);
        searchView=findViewById(R.id.searchView);

        database = FirebaseDatabase.getInstance().getReference("Product_Clothing");
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);

                return true;
            }


        });

        list_of_pots = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        myAdapter = new MyAdapter1(this,list_of_pots);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("Product_Clothing").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
                {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {
                        if (!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list)
                            {
                                String name= (String) d.get("Product_Name");
                                String price = (String) d.get("Product_Price");

                                Pottery_POJO c = new Pottery_POJO();
                                c.setProduct_name(name);
                                c.setProduct_price(price);
                                list_of_pots.add(c);
                            }
                            myAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(Clothing_List.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Clothing_List.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterList(String text) {
        List<Pottery_POJO> filteredList = new ArrayList<>();
        for(Pottery_POJO pottery_pojo:list_of_pots){
            if(pottery_pojo.getProduct_name().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(pottery_pojo);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"no data found",Toast.LENGTH_LONG).show();
        }else{
            myAdapter.setFilteredList(filteredList);
        }


    }
}