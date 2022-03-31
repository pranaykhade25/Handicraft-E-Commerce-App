package com.example.handicraftecommerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class paymentdetails extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName, mphonenumber,memail,mbuildingname, mroadname, mpincode, mcity, mstate, mlocation;
    Button btn_place_order;
    TextView btn, product_name;
    RadioGroup rg_payment,rg_size;

    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentdetails);

        mFullName = findViewById(R.id.firstname);
        memail = findViewById(R.id.inputEmail);
        mphonenumber = findViewById(R.id.PhoneNumber);
        mbuildingname = findViewById(R.id.buildingname);
        mroadname = findViewById(R.id.roadname);
        mpincode = findViewById(R.id.pincode);
        mcity = findViewById(R.id.city);
        mstate = findViewById(R.id.state);
        mlocation = findViewById(R.id.location);
        btn_place_order = findViewById(R.id.place_order);
        product_name = (TextView) findViewById(R.id.selected_product);
        rg_payment = (RadioGroup) findViewById(R.id.rg12);
        rg_size = (RadioGroup) findViewById(R.id.rgsizegrp);

        Intent k = getIntent();
        String product_name_price1 = k.getStringExtra("product_name_price");
        product_name.setText(product_name_price1);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = memail.getText().toString().trim();
                String FullName = mFullName.getText().toString().trim();
                String PhoneNumber = mphonenumber.getText().toString().trim();
                String BuildingName = mbuildingname.getText().toString().trim();
                String RoadName = mroadname.getText().toString().trim();
                String Pincode = mpincode.getText().toString().trim();
                String City = mcity.getText().toString().trim();
                String State = mstate.getText().toString().trim();
                String Location = mlocation.getText().toString().trim();
                String payment_mode = "";
                String size12 = "";


                if (TextUtils.isEmpty(FullName)) {
                    mFullName.setError("Name is Required ..");
                    return;
                }
                if (TextUtils.isEmpty(PhoneNumber)) {
                    mphonenumber.setError("phone number is Required..");
                    return;
                }
                if (TextUtils.isEmpty(BuildingName)) {
                    mbuildingname.setError("Building name is Required..");
                    return;
                }

                if (TextUtils.isEmpty(RoadName)) {
                    mroadname.setError("Road name is required");
                    return;
                }

                if (TextUtils.isEmpty(Pincode)) {
                    mpincode.setError("pincode is required");
                    return;
                }
                if (TextUtils.isEmpty(City)) {
                    mcity.setError("city is required");
                    return;
                }

                if (TextUtils.isEmpty(State)) {
                    mcity.setError("State is required");
                    return;
                }


                switch (rg_size.getCheckedRadioButtonId()) {

                    case R.id.large:
                        size12 += "Large";
                        break;

                    case R.id.small:
                        size12 += "Small";
                        break;

                    case R.id.xxl:
                        size12 += "XXL";
                        break;

                    case R.id.xl:
                        size12 += "XL";
                        break;
                }


                switch (rg_payment.getCheckedRadioButtonId()) {
                    case R.id.cash_delivery:
                        payment_mode += "Cash On Delivery";
                        break;

                    case R.id.gpay:
                        payment_mode += "Google Pay";
                        break;
                }

                //register the user in firebase
                String finalSize = size12;
                String finalPayment_mode = payment_mode;
                fAuth.createUserWithEmailAndPassword(Email,Pincode).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            //Toast.makeText(paymentdetails.this, "User created Successfully ", Toast.LENGTH_LONG).show();
                            userId = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("Customer_Payment_Details").document(userId);
                            Map<String, Object> users = new HashMap<>();
                            users.put("Email", Email);
                            users.put("Fullname", FullName);
                            users.put("Phonenumber", PhoneNumber);
                            users.put("Buildingname", BuildingName);
                            users.put("Roadname", RoadName);
                            users.put("Pincode", Pincode);
                            users.put("City", City);
                            users.put("State",State);
                            users.put("Ordered_Product",product_name_price1);
                            users.put("Payment_Mode", finalPayment_mode);
                            users.put("Size", finalSize);

                            documentReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onsuccess :Your Order is placed successfully" + userId);
                                    Toast.makeText(paymentdetails.this, "Your Order is placed successfully", Toast.LENGTH_LONG).show();

                                    Intent i = new Intent(paymentdetails.this, Success_Order_placed.class);
                                    startActivity(i);

                                }
                            });

                        } else {
                            Toast.makeText(paymentdetails.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}