package com.example.handicraftecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText admin_email,admin_password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_email = (EditText) findViewById(R.id.inputEmail);
        admin_password = (EditText) findViewById(R.id.inputPassword);
        login = (Button) findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res="";
                String a1_email = admin_email.getText().toString();
                String a2_password = admin_password.getText().toString();
                if(a1_email.equals("admin123@gmail.com") && a2_password.equals("admin123"))
                {
                    res = "Successfull Login";
                    Intent i = new Intent(AdminLogin.this,Admin_Categories.class);
                    startActivity(i);
                }
                else
                    res = "Wrong Username or Password";
                Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
            }
        });
    }
}