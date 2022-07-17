package com.viajeros.pe.firebase.repository;

import com.viajeros.pe.firebase.model.TouristPlace;

public class TouristPlaceRepository extends FirebaseRepository<TouristPlace> {

    private static TouristPlaceRepository instance;

    public synchronized static TouristPlaceRepository getInstance() {
        if (instance == null) {
            instance = new TouristPlaceRepository();
        }
        return instance;
    }

    private TouristPlaceRepository() {
        super(TouristPlace.class);
    }

}
