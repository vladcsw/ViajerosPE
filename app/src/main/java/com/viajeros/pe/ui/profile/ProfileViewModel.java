package com.viajeros.pe.ui.profile;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.User;
import com.viajeros.pe.firebase.repository.UserRepository;
import com.viajeros.pe.firebase.service.AuthService;

public class ProfileViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final FirebaseUser currentUser  = AuthService.firebaseGetCurrentUser();
    private MultipleDocumentReferenceLiveData<User, Query> allLiveData;

    public ProfileViewModel() {
        this.userRepository = UserRepository.getInstance();
    }

    public DocumentReferenceFirebaseLiveData<User> getCurrentUserData(){
        return userRepository.findById(currentUser.getUid());
    }

    public MultipleDocumentReferenceLiveData<User, Query> getAllLiveData (){
        if (allLiveData == null){
            allLiveData = userRepository.findAll();
        }
        return allLiveData;
    }

    public void updateUser(User user){
        userRepository.update(user);
    }

}