package com.viajeros.pe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.viajeros.pe.firebase.service.AuthService;

public class LoginEmailActivity extends AppCompatActivity {

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        buttonLogin = findViewById(R.id.loginEmail_buttonLogin);
        Context mContext = this.getApplicationContext();

        buttonLogin.setOnClickListener(view -> {
            AuthService.firebaseSingOut();
            String email = ((EditText) findViewById(R.id.loginEmail_editTextEmail)).getText().toString();
            String password = ((EditText) findViewById(R.id.loginEmail_editTextPassword)).getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(mContext, "Correo o contraseña vacios.", Toast.LENGTH_SHORT).show();
            } else {
                Task<AuthResult> auth = new AuthService().firebaseSinInWithEmail(email, password);
                auth.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(mContext, "Autenticación exitosa.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginEmailActivity.this, MainActivityMenu.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(mContext, "Correo o contraseña incorrecto.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }
}