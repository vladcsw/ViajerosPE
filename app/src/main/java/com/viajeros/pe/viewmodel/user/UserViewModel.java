package com.viajeros.pe.viewmodel.user;

import androidx.lifecycle.ViewModel;


import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;

import com.viajeros.pe.firebase.model.User;
import com.viajeros.pe.firebase.repository.TravellerRepository;
import com.viajeros.pe.firebase.repository.UserRepository;
import com.viajeros.pe.firebase.service.AuthService;

public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;

    public UserViewModel(){
        this.userRepository = UserRepository.getInstance();
    }

    public void saveUser(User user){
        userRepository.saveUser(user);
        TravellerRepository travellerRepository = TravellerRepository.getInstance();
        travellerRepository.saveTraveller(AuthService.firebaseGetCurrentUser().getUid());
    }


    public DocumentReferenceFirebaseLiveData<User> getUser(String uid){
        return userRepository.findById(uid);
    }


}
