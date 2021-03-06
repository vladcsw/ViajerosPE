package com.viajeros.pe.firebase.livedata;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.viajeros.pe.firebase.model.FirebaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentReferenceFirebaseLiveData<T extends FirebaseEntity> extends MutableLiveData<T> implements EventListener<DocumentSnapshot> {

    protected static String TAG = DocumentReferenceFirebaseLiveData.class.getSimpleName();
    protected T entity;
    protected ListenerRegistration listenerRegistration = () -> {
    };

    private final DocumentReference documentReference;
    protected final Class<T> entityClass;

    public DocumentReferenceFirebaseLiveData(DocumentReference documentReference, Class<T> entityClass) {
        this.documentReference = documentReference;
        this.entityClass = entityClass;
    }

    @Override
    protected void onActive() {
        this.listenerRegistration = this.documentReference.addSnapshotListener(this);
        super.onActive();
    }

    @Override
    protected void onInactive() {
        this.listenerRegistration.remove();
        super.onInactive();
    }

    @Override
    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException error) {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            Log.e(TAG, "Updating");
            this.entity = documentSnapshot.toObject(this.entityClass);
            assert this.entity != null;
            this.entity.setDocumentId(documentSnapshot.getId());
            this.setValue(entity);
        } else if (error != null) {
            Log.e(TAG, error.getMessage(), error.getCause());
        }
    }
}
