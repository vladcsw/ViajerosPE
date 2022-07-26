package com.viajeros.pe.firebase.model;

import com.google.firebase.firestore.Exclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class FirebaseEntity {
    private String documentId;

    @Exclude
    public String getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(String id) {
        this.documentId = id;
    }
}
