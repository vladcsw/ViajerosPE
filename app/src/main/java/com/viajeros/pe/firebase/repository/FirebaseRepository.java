package com.viajeros.pe.firebase.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.FirebaseEntity;
import com.viajeros.pe.firebase.utils.GenericMapper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FirebaseRepository <E extends FirebaseEntity> implements CrudFirebaseRepository<E, String> {

    private static final String TAG = FirebaseRepository.class.getSimpleName();
    protected final CollectionReference collectionReference;
    protected final Class<E> entityClass;

    public FirebaseRepository(@NotNull Class<E> entityClass){
        super();
        Log.e(TAG, "FIREBASE_REPOSITORY");
        this.entityClass = entityClass;
        this.collectionReference = FirebaseFirestore.getInstance().collection(this.entityClass.getSimpleName());
    }

    @Override
    public MultipleDocumentReferenceLiveData<E, Query> findAll() {
        return new MultipleDocumentReferenceLiveData<>(this.collectionReference, this.entityClass);
    }

    @Override
    public DocumentReferenceFirebaseLiveData<E> findById(String identifier) {
        return new DocumentReferenceFirebaseLiveData<>(this.collectionReference.document(identifier), this.entityClass);
    }

    @Override
    public void save(E entity) {
        Log.e("saveH", "save");
        this.collectionReference.document().set(entity);

    }

    @Override
    public void saveAll(List<E> entities) {
        Log.e("save2", "save");
        WriteBatch batch = FirebaseFirestore.getInstance().batch();
        for (E entity : entities) {
            DocumentReference documentReference = this.collectionReference.document();
            batch.set(documentReference, entity);
        }
        batch.commit();
    }

    @Override
    public void update(E entity) {
        this.collectionReference.document(entity.getDocumentId()).set(entity, SetOptions.mergeFields("itemList"));
    }

    @Override
    public void updateAll(List<E> entities) throws IllegalAccessException {
        WriteBatch batch = FirebaseFirestore.getInstance().batch();
        for (E entity : entities) {
            DocumentReference documentReference = this.collectionReference.document(entity.getDocumentId());
            batch.update(documentReference, GenericMapper.convertMapToObject(entity));
        }
        batch.commit();
    }

    @Override
    public Task<Void> delete(E entity) {
        Log.e("del", entity.getDocumentId());
        return this.collectionReference.document(entity.getDocumentId()).delete();
    }

    @Override
    public void deleteAll(List<E> entities) {
        Log.e(TAG, "deleteAll()");
        WriteBatch batch = FirebaseFirestore.getInstance().batch();
        for (E entity : entities) {
            batch.delete(this.collectionReference.document(entity.getDocumentId()));
        }
        batch.commit();
    }

    public String getIdX() {
        return this.collectionReference.document("X345eEfB6IHEnfRPLebN").collection("itemList").document().getId();

    }

    public void setIdX(@NonNull E entity, String h) {
        Log.e("vex", "vex");
        entity.setDocumentId(h);
    }

}
