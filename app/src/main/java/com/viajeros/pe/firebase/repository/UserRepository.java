package com.viajeros.pe.firebase.repository;

import com.google.firebase.auth.FirebaseUser;
import com.viajeros.pe.firebase.model.User;
import com.viajeros.pe.firebase.service.AuthService;

public class UserRepository extends FirebaseRepository<User>{

    private static UserRepository instance;

    public synchronized static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private UserRepository() {
        super(User.class);
    }

    public void saveUser(User user) {
        FirebaseUser authUser = AuthService.firebaseGetCurrentUser();

        this.saveWithExistentDocumentId(authUser.getUid(), new User(
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone()
        ));
    }

    private void saveWithExistentDocumentId(String id, User user) {
        this.collectionReference.document(id).set(user);
    }
}
