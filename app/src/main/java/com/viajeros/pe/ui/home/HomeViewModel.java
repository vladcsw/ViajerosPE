package com.viajeros.pe.ui.home;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.TouristPlace;
import com.viajeros.pe.firebase.repository.TouristPlaceRepository;

public class HomeViewModel extends ViewModel {

    private final TouristPlaceRepository touristPlaceRepository;
    private MultipleDocumentReferenceLiveData<TouristPlace, Query> allLiveData;

    public HomeViewModel(){
        this.touristPlaceRepository = TouristPlaceRepository.getInstance();
    }

    public MultipleDocumentReferenceLiveData<TouristPlace, Query> getAllLiveData() {
        if (allLiveData == null) {
            allLiveData = touristPlaceRepository.findAll();
        }
        return allLiveData;
    }

}