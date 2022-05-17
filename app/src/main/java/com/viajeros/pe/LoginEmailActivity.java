package com.viajeros.pe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginEmailActivity extends AppCompatActivity {

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        buttonLogin = findViewById(R.id.loginEmail_buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginEmailActivity.this, MainActivityMenu.class);
                startActivity(i);
                finish();
            }
        });

    }
}