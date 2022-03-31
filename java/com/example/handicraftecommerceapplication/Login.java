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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoinButton;
    TextView mCreateButton;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCreateButton =(TextView) findViewById(R.id.textViewSignup);
        mEmail = (EditText) findViewById(R.id.inputEmail);
        mPassword = (EditText) findViewById(R.id.inputPassword);
        mLoinButton=(Button) findViewById(R.id.btnlogin);
        fAuth=FirebaseAuth.getInstance();

        mLoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password =mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required ..");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("password is Required..");
                    return;
                }
                if(password.length()<6){
                    mPassword.setError("Password Must be >=6 Characters");
                    return;
                }
                //authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in Successfully ",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Category.class));
                        }else {
                            Toast.makeText(Login.this,"Error ! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register_Customer.class);
                startActivity(i);
            }
        });
    }
}