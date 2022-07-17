package com.viajeros.pe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button buttonRegister, buttonLoginEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonRegister = findViewById(R.id.login_buttonRegister);
        buttonLoginEmail = findViewById(R.id.login_buttonLoginEmail);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
        buttonLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, LoginEmailActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}