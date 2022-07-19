package com.viajeros.pe.ui.home;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.repository.ToDoListRepository;

public class ToDoViewModel extends ViewModel {

    private final ToDoListRepository toDoListRepository;

    private MultipleDocumentReferenceLiveData<ToDoList, Query> allLiveData;
    private DocumentReferenceFirebaseLiveData<ToDoList> singleLiveData;

    public ToDoViewModel(){
        this.toDoListRepository = ToDoListRepository.getInstance();
    }

    // Returns all documents saved in FireStore
    public MultipleDocumentReferenceLiveData<ToDoList, Query> getAllLiveData() {
        if(allLiveData == null){
            allLiveData = toDoListRepository.findAll();
        }
        return allLiveData;
    }

    // Returns a document by its own id
    public DocumentReferenceFirebaseLiveData<ToDoList> finById(String id){
        if(singleLiveData == null){
            singleLiveData = toDoListRepository.findById(id);
        }
        return singleLiveData;
    }

    // Returns all documents saved in FireStore by user id
    public MultipleDocumentReferenceLiveData<ToDoList, Query> findByUser(String userId){
        if(allLiveData == null){
            allLiveData = toDoListRepository.finByUser(userId);
        }
        return allLiveData;
    }

    // Returns all documents saved in FireStore by binnacle id
    public MultipleDocumentReferenceLiveData<ToDoList, Query> findByBinnacle(String binnacleId){
        if(allLiveData == null) {
            allLiveData = toDoListRepository.findByBinnacle(binnacleId);
        }
        return allLiveData;
    }

    // Save a document in FireStore
    public void save(ToDoList toDoList){
        toDoListRepository.save(toDoList);
    }

    // Delete a document in FireStore
    public void delete(ToDoList toDoList){
        toDoListRepository.delete(toDoList);
    }

    // Update a document in FireStore
    public void update(ToDoList toDoList){
        toDoListRepository.update(toDoList);
    }
}
