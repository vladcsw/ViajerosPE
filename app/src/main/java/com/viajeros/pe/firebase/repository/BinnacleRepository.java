package com.viajeros.pe.firebase.repository;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.Binnacle;
import com.viajeros.pe.firebase.model.ToDoList;

public class BinnacleRepository extends FirebaseRepository<Binnacle> {

    private static BinnacleRepository instance;

    public synchronized static BinnacleRepository getInstance() {
        if (instance == null) {
            instance = new BinnacleRepository();
        }
        return instance;
    }

    public void saveWithExistentId(Binnacle binnacle , String id){
        this.collectionReference.document(id).set(binnacle);

    }

    public void removeWithExistentId(String id){
        this.collectionReference.document(id).delete();

    }

    public MultipleDocumentReferenceLiveData<Binnacle, Query> finByUser(String uid) {
        return new MultipleDocumentReferenceLiveData<>(collectionReference.whereEqualTo("userId", uid).orderBy("name"), entityClass);
    }




    private BinnacleRepository() {
        super(Binnacle.class);
    }
}
