package com.viajeros.pe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.viajeros.pe.firebase.model.User;
import com.viajeros.pe.firebase.service.AuthService;
import com.viajeros.pe.viewmodel.user.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    Button button_addAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        UserViewModel userViewModel = new UserViewModel();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button_addAccount = findViewById(R.id.register_button_addAccount);
        button_addAccount.setOnClickListener(view -> {

            String name = ((EditText)findViewById(R.id.register_editText_names)).getText().toString();
            String last_name = ((EditText)findViewById(R.id.register_editText_lastNames)).getText().toString();
            String email = ((EditText)findViewById(R.id.register_editText_email)).getText().toString();
            String phone = ((EditText)findViewById(R.id.register_editText_phone)).getText().toString();
            String password = ((EditText)findViewById(R.id.register_editText_password)).getText().toString();

            User user = new User(name, last_name, email, Integer.parseInt(phone));

            Task<AuthResult> auth = new AuthService().firebaseRegister(email, password);
            auth.addOnCompleteListener(task -> {
               if(task.isSuccessful()) {
                   userViewModel.saveUser(user);
                   Toast.makeText(getApplicationContext(), "Cuenta creada correctamente", Toast.LENGTH_LONG).show();
                   Intent i = new Intent(RegisterActivity.this, MainActivityMenu.class);
                   AuthService.firebaseAuthenticationWithCredential(email, password);
                   startActivity(i);
                   finish();
               }else{
                   Toast.makeText(getApplicationContext(), "Error creando la cuenta.", Toast.LENGTH_LONG).show();
               }
            });
        });

    }
}