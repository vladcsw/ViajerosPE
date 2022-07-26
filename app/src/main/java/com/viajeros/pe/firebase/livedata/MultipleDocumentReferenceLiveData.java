package com.viajeros.pe.firebase.livedata;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.viajeros.pe.firebase.model.FirebaseEntity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultipleDocumentReferenceLiveData<T extends FirebaseEntity, L extends Query> extends LiveData<List<T>> implements EventListener<QuerySnapshot> {

    protected static String TAG = MultipleDocumentReferenceLiveData.class.getSimpleName();

    private final L multipleDocuments;
    protected ListenerRegistration listenerRegistration = () -> {
    };

    protected final List<T> entityList = new ArrayList<>();
    private final Class<T> entityClass;

    public MultipleDocumentReferenceLiveData(L multipleDocuments, Class<T> entityClass) {
        this.multipleDocuments = multipleDocuments;
        this.entityClass = entityClass;

    }

    @Override
    protected void onActive() {
        this.listenerRegistration = this.multipleDocuments.addSnapshotListener(this);
        super.onActive();
    }

    @Override
    protected void onInactive() {
        this.listenerRegistration.remove();
        super.onInactive();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot querySnapshot, @Nullable FirebaseFirestoreException error) {
        if (querySnapshot != null && !querySnapshot.isEmpty()) {
            Log.e(TAG, "Updating");
            this.entityList.clear();
            this.entityList.addAll(querySnapshot.toObjects(this.entityClass));
            for (int i = 0; i < querySnapshot.size(); i++) {
                this.entityList.get(i).setDocumentId(querySnapshot.getDocuments().get(i).getId());
            }
            this.setValue(entityList);
        } else if (error != null) {
            Log.e(TAG, error.getMessage(), error.getCause());
        }
    }
}
