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

public class Display_Received_Orders extends AppCompatActivity {

    RecyclerView recyclerView;
    private SearchView searchView;
    DatabaseReference database;
    MyAdapter2 myAdapter;
    ArrayList<Order_POJO> list_of_orders;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_received_orders);

        recyclerView = findViewById(R.id.Received_Orders);
        searchView=findViewById(R.id.searchView);

        database = FirebaseDatabase.getInstance().getReference("Customer_Payment_Details");
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

        list_of_orders = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        myAdapter = new MyAdapter2(this,list_of_orders);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("Customer_Payment_Details").get()
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
                                String Buildingname= (String) d.get("Buildingname");
                                String City = (String) d.get("City");
                                String Email = (String) d.get("Email");
                                String Fullname = (String) d.get("Fullname");
                                String Ordered_Product= (String) d.get("Ordered_Product");
                                String Payment_Mode = (String) d.get("Payment_Mode");
                                String Phonenumber = (String) d.get("Phonenumber");
                                String Pincode= (String) d.get("Pincode");
                                String Roadname = (String) d.get("Roadname");
                                String State = (String) d.get("State");
                                String Size = (String) d.get("Size");

                                Order_POJO op = new Order_POJO();
                                op.setBuildingname(Buildingname);
                                op.setCity(City);
                                op.setEmail(Email);
                                op.setFullname(Fullname);
                                op.setOrdered_Product(Ordered_Product);
                                op.setPayment_Mode(Payment_Mode);
                                op.setPhonenumber(Phonenumber);
                                op.setPincode(Pincode);
                                op.setRoadname(Roadname);
                                op.setState(State);
                                op.setSize(Size);
                                list_of_orders.add(op);
                            }
                            myAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(Display_Received_Orders.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Display_Received_Orders.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterList(String text) {
        List<Order_POJO> filteredList = new ArrayList<>();
        for(Order_POJO pottery_pojo:list_of_orders){
            if(pottery_pojo.getState().toLowerCase().contains(text.toLowerCase())){
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