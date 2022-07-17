package com.viajeros.pe.firebase.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.FirebaseEntity;

import java.util.List;

public interface CrudFirebaseRepository <E extends FirebaseEntity, I> {

    MultipleDocumentReferenceLiveData<E, Query> findAll();
    DocumentReferenceFirebaseLiveData<E> findById(I identifier);
    void save(E entity);
    void saveAll(List<E> entities);
    void update(E entity);
    void updateAll(List<E> entities) throws IllegalAccessException;
    Task<Void> delete(E entity);
    void deleteAll(List<E> entities);

}
