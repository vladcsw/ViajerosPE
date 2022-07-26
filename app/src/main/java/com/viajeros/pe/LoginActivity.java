package com.viajeros.pe;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.viajeros.pe.firebase.model.User;
import com.viajeros.pe.firebase.service.AuthService;
import com.viajeros.pe.viewmodel.user.UserViewModel;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private final int GOOGLE_SIGN_IN = 100;
    private User Ownuser = new User();

    Button buttonRegister, buttonLoginEmail, buttonGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonRegister = findViewById(R.id.login_buttonRegister);
        buttonLoginEmail = findViewById(R.id.login_buttonLoginEmail);

        buttonGoogle = findViewById(R.id.login_buttonLoginGoogle);

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

        buttonGoogle.setOnClickListener(view -> {

            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            GoogleSignInClient googleSignIn = GoogleSignIn.getClient(this, googleSignInOptions);
            UserViewModel userViewModel = new UserViewModel();

            if (AuthService.firebaseGetCurrentUser() != null) {
                userViewModel.getUser(AuthService.firebaseGetCurrentUser().getUid()).observe(this, user -> {
                    Ownuser = user;
                });
            }
            googleSignIn.signOut();
            startActivityForResult(googleSignIn.getSignInIntent(), GOOGLE_SIGN_IN);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == GOOGLE_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(result -> {
                        if (result.isSuccessful()) {

                            if(Ownuser.getDocumentId() == null){
                                UserViewModel userViewModel = new UserViewModel();
                                userViewModel.saveUser(new User(
                                        account.getGivenName(),
                                        account.getFamilyName(),
                                        account.getEmail(),
                                        0
                                ));
                            }

                            Toast.makeText(this, "Login successful.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, MainActivityMenu.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(this, "Credential are wrong.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        } catch (ApiException e) {
            Log.e("TAG", "Something is going wrong");
        }
    }
}