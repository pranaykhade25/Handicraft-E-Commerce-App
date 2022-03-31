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

public class Customer_List extends AppCompatActivity {

    RecyclerView recyclerView;
    private SearchView searchView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Customer_POJO> list_of_customers;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        recyclerView = findViewById(R.id.Registered_Customers);
        searchView=findViewById(R.id.searchView);

        database = FirebaseDatabase.getInstance().getReference("Customer");
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

        list_of_customers = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        myAdapter = new MyAdapter(this,list_of_customers);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("Customer").get()
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
                                String full= (String) d.get("Full_name");
                                String email = (String) d.get("Email");
                                String password = (String) d.get("Password");
                                String gender = (String) d.get("Gender");

                                Customer_POJO c = new Customer_POJO();
                                c.setFull_name(full);
                                c.setEmail(email);
                                c.setPassword(password);
                                c.setGender(gender);
                                list_of_customers.add(c);
                            }
                            myAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(Customer_List.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Customer_List.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterList(String text) {
        List<Customer_POJO> filteredList = new ArrayList<>();
        for(Customer_POJO pottery_pojo:list_of_customers){
            if(pottery_pojo.getFull_name().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(pottery_pojo);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_LONG).show();
        }else{
            myAdapter.setFilteredList(filteredList);
        }
    }
}