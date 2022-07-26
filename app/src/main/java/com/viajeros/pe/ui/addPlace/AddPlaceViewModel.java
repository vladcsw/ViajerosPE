package com.viajeros.pe.ui.addPlace;

import androidx.lifecycle.ViewModel;

import com.viajeros.pe.firebase.model.TouristPlace;
import com.viajeros.pe.firebase.repository.TouristPlaceRepository;

public class AddPlaceViewModel extends ViewModel {

    private final TouristPlaceRepository touristPlaceRepository;

    public AddPlaceViewModel(){
        this.touristPlaceRepository = TouristPlaceRepository.getInstance();
    }

    public void save(TouristPlace touristPlace){
        touristPlaceRepository.save(touristPlace);
    }

}