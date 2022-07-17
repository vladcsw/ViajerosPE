package com.viajeros.pe.firebase.repository;

import com.viajeros.pe.firebase.model.Province;

public class ProvinceRepository extends FirebaseRepository<Province> {

    private static ProvinceRepository instance;

    public synchronized static ProvinceRepository getInstance() {
        if (instance == null) {
            instance = new ProvinceRepository();
        }
        return instance;
    }

    private ProvinceRepository() {
        super(Province.class);
    }

}
