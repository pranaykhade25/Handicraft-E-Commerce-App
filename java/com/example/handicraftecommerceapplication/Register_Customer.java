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

public class Register_Customer extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mConfirmPassword;
    Button mRegisterButton;
    TextView btn;
    RadioGroup gendeer;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);

        mFullName=(EditText) findViewById(R.id.inputUsername);
        mEmail =(EditText) findViewById(R.id.inputEmail);
        mPassword=(EditText) findViewById(R.id.inputPassword);
        mConfirmPassword=(EditText) findViewById(R.id.inputConfirmPassword);
        mRegisterButton =(Button) findViewById(R.id.btnRegister);
        btn =(TextView) findViewById(R.id.alreadyHaeAccount);
        gendeer=(RadioGroup) findViewById(R.id.radioGroup);

        final String[] gender = {""};

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Register_Customer.class));
            finish();
        }

        mRegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmpassword = mConfirmPassword.getText().toString().trim();
                String fullname = mFullName.getText().toString();

                switch (gendeer.getCheckedRadioButtonId())
                {
                    case R.id.radiomale:
                        gender[0] = "Male";
                        break;

                    case R.id.radiofemale:
                        gender[0] = "Female";
                        break;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required ..");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("password is Required..");
                    return;
                }
                if (TextUtils.isEmpty(confirmpassword)) {
                    mConfirmPassword.setError("password is Required..");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be >=6 Characters");
                    return;
                }

                if (confirmpassword.length() < 6) {
                    mConfirmPassword.setError("Password Must be >=6 Characters");
                    return;
                }

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Register_Customer.this, "User created Successfully ", Toast.LENGTH_LONG).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fstore.collection("Customer").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Full_name",fullname);
                            user.put("Email",email);
                            user.put("Password",password);
                            user.put("Gender", gender[0]);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onsuccess:User profile is created"+userID );
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), Register_Customer.class));

                        } else {
                            Toast.makeText(Register_Customer.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register_Customer.this, Login.class);
                startActivity(i);
            }
        });

    }
}
