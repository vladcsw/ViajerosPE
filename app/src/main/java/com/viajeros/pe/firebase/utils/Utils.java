package com.viajeros.pe.firebase.utils;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utils {

    // Generates id
    public String getDocumentIdGenerated(String documentCollectionName){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection(documentCollectionName).document();
        return documentReference.getId();
    }
}
