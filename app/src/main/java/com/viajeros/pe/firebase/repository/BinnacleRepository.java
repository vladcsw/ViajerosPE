package com.viajeros.pe.firebase.repository;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.Binnacle;

public class BinnacleRepository extends FirebaseRepository<Binnacle> {

    private static BinnacleRepository instance;

    public synchronized static BinnacleRepository getInstance() {
        if (instance == null) {
            instance = new BinnacleRepository();
        }
        return instance;
    }

    public MultipleDocumentReferenceLiveData<Binnacle, Query> finByUser(String uid) {
        return new MultipleDocumentReferenceLiveData<>(collectionReference.whereEqualTo("userID", uid), entityClass);
    }

    private BinnacleRepository() {
        super(Binnacle.class);
    }
}
