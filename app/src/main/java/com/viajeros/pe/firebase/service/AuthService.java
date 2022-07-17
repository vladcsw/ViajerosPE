package com.viajeros.pe.firebase.service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class AuthService {

    private static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public Task<AuthResult> firebaseSinInWithEmail(String email, String password){
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }

    public void firebaseSingOut() {
        firebaseAuth.signOut();
    }

    public static FirebaseUser firebaseGetCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    public Task<AuthResult> firebaseRegister(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    public static Task<Void> firebaseAuthenticationWithCredential(String email, String password) {
        return Objects.requireNonNull(firebaseAuth.getCurrentUser()).reauthenticate(
                EmailAuthProvider.getCredential(email, password)
        );
    }

}
