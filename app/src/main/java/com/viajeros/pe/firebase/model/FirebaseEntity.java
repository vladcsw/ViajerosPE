package com.viajeros.pe.firebase.model;

import com.google.firebase.firestore.Exclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseEntity {
    private String documentId;

    @Exclude
    public String getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(String id) {
        this.documentId = id;
    }
}
