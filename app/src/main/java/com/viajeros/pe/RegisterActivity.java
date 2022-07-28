package com.viajeros.pe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.viajeros.pe.firebase.model.Binnacle;
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.model.ToDoListItem;
import com.viajeros.pe.firebase.model.TouristPlace;
import com.viajeros.pe.firebase.model.User;
import com.viajeros.pe.firebase.service.AuthService;
import com.viajeros.pe.firebase.utils.Utils;
import com.viajeros.pe.ui.home.HomeSelectViewModel;
import com.viajeros.pe.ui.home.HomeViewModel;
import com.viajeros.pe.ui.home.ToDoViewModel;
import com.viajeros.pe.viewmodel.user.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Button button_addAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        UserViewModel userViewModel = new UserViewModel();
        HomeSelectViewModel homeSelectViewModel = new HomeSelectViewModel();
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


                   FirebaseUser authUser = AuthService.firebaseGetCurrentUser();

                   userViewModel.saveUser(user);

                   HomeViewModel homeViewModel;
                   List<TouristPlace> savePlace = new ArrayList<>(); // List of TouristPlaces
                   homeViewModel = new HomeViewModel(); // Calls view model Home
                   // Get all tourist places saved id FireStore and fill the list of TouristPlaces
                   homeViewModel.getAllLiveData().observe(this, data ->{

                       savePlace.addAll(data);
                       Utils utils = new Utils(); // Own class utils created
                       String binnacleIdGenerated = utils.getDocumentIdGenerated("Binnacle");
                       //String uid = AuthService.firebaseGetCurrentUser().getUid(); // Get id of current user logged
                       Binnacle binnacle = new Binnacle(authUser.getUid(), savePlace, "name"); // Object Binnacle to save
                       binnacle.setDocumentId(binnacleIdGenerated);
                       homeSelectViewModel.saveWithIdGenerated(binnacle, binnacleIdGenerated); // Save binnacle in FireStore
                       List<ToDoListItem> itemList = new ArrayList<>();
                       ToDoList toDoList = new ToDoList(authUser.getUid(), binnacleIdGenerated, true, itemList);
                       ToDoViewModel todo = new ToDoViewModel();
                       todo.save(toDoList);
                   });

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