package com.viajeros.pe.firebase.repository;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.ToDoList;

public class ToDoListRepository extends FirebaseRepository<ToDoList>{

    private static ToDoListRepository instance;

    public synchronized static ToDoListRepository getInstance() {
        if (instance == null) {
            instance = new ToDoListRepository();
        }
        return instance;
    }

    public MultipleDocumentReferenceLiveData<ToDoList, Query> finByUser(String uid) {
        return new MultipleDocumentReferenceLiveData<>(collectionReference.whereEqualTo("userId", uid), entityClass);
    }

    private ToDoListRepository() {
        super(ToDoList.class);
    }
}
