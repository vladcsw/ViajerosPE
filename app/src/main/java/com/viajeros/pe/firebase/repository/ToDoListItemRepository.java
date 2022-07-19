package com.viajeros.pe.firebase.repository;

import com.viajeros.pe.firebase.model.ToDoListItem;

public class ToDoListItemRepository extends FirebaseRepository<ToDoListItem> {

    private static ToDoListItemRepository instance;

    public synchronized static ToDoListItemRepository getInstance() {
        if (instance == null) {
            instance = new ToDoListItemRepository();
        }
        return instance;
    }

    private ToDoListItemRepository() {
        super(ToDoListItem.class);
    }
}
