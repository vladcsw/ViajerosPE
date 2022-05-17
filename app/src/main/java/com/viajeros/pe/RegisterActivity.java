package com.viajeros.pe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button button_addAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button_addAccount = findViewById(R.id.register_button_addAccount);
        button_addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cuenta creada correctamente", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RegisterActivity.this, MainActivityMenu.class);
                startActivity(i);
                finish();
            }
        });

    }
}