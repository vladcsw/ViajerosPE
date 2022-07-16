package com.viajeros.pe.firebase.repository;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.ToDoListItem;

public class ToDoListItemRepository extends FirebaseRepository<ToDoListItem> {

    private static ToDoListItemRepository instance;

    public synchronized static ToDoListItemRepository getInstance() {
        if (instance == null) {
            instance = new ToDoListItemRepository();
        }
        return instance;
    }

    public MultipleDocumentReferenceLiveData<ToDoListItem, Query> finByToDoList(String ToDoListId) {
        return new MultipleDocumentReferenceLiveData<>(collectionReference.whereEqualTo("todolistId", ToDoListId), entityClass);
    }

    private ToDoListItemRepository() {
        super(ToDoListItem.class);
    }
}
