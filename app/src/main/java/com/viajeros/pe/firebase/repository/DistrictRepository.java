package com.viajeros.pe.firebase.repository;

import com.viajeros.pe.firebase.model.District;

public class DistrictRepository extends FirebaseRepository<District> {

    private static DistrictRepository instance;

    public synchronized static DistrictRepository getInstance() {
        if (instance == null) {
            instance = new DistrictRepository();
        }
        return instance;
    }

    private DistrictRepository() {
        super(District.class);
    }

}
