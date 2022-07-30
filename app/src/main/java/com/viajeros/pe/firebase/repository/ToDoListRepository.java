package com.viajeros.pe.firebase.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.Binnacle;
import com.viajeros.pe.firebase.model.ToDoList;

import org.w3c.dom.Entity;

public class ToDoListRepository extends FirebaseRepository<ToDoList> {

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

    public MultipleDocumentReferenceLiveData<ToDoList, Query> findByBinnacle(String bid) {
        return new MultipleDocumentReferenceLiveData<>(collectionReference.whereEqualTo("binnacleId", bid), entityClass);


    }
    public void saveWithExistentId(ToDoList ToDo , String id){
        this.collectionReference.document(id).set(ToDo);

    }
    private ToDoListRepository() {
        super(ToDoList.class);
    }

    public Boolean findDocument(String x){

        Query query = collectionReference.whereEqualTo("binnacleId", x);
        final boolean[] h = {false};
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        h[0] =true;

                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });



        return h[0];
    }

}
