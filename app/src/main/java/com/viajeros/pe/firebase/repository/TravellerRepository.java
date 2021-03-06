package com.viajeros.pe.firebase.repository;

import com.viajeros.pe.firebase.model.Traveller;

public class TravellerRepository extends FirebaseRepository<Traveller>{

    private static TravellerRepository instance;

    public synchronized static TravellerRepository getInstance() {
        if (instance == null) {
            instance = new TravellerRepository();
        }
        return instance;
    }

    private TravellerRepository(){
        super(Traveller.class);
    }

    public void saveTraveller(String uid){
        instance.save(new Traveller((uid)));
    }

}
