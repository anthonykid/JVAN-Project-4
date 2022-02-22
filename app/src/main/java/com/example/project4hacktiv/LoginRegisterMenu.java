package com.example.project4hacktiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginRegisterMenu extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin,btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_menu);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnregister = findViewById(R.id.btnRegister);
        btnregister.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Intent explicit1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(explicit1);
                break;

            case R.id.btnRegister:
                Intent explicit2 = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(explicit2);
                break;

            default:
                break;
        }
    }}