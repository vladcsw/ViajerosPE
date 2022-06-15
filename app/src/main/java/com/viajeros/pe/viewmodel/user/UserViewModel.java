package com.viajeros.pe.viewmodel.user;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.User;
import com.viajeros.pe.firebase.repository.TravellerRepository;
import com.viajeros.pe.firebase.repository.UserRepository;
import com.viajeros.pe.firebase.service.AuthService;

public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;
    private MultipleDocumentReferenceLiveData<User, ?extends Query> allLiveData;

    public UserViewModel(){
        this.userRepository = UserRepository.getInstance();
    }

    public void saveUser(User user){
        userRepository.saveUser(user);
        TravellerRepository travellerRepository = TravellerRepository.getInstance();
        travellerRepository.saveTraveller(AuthService.firebaseGetCurrentUser().getUid());
    }

    public MultipleDocumentReferenceLiveData<User, ?extends Query> getAllLiveData(){
        if (allLiveData == null) {
            return userRepository.findAll();
        }
        return this.allLiveData;
    }

}
