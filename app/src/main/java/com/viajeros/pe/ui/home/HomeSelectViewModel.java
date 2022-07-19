package com.viajeros.pe.ui.home;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.Binnacle;
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.repository.BinnacleRepository;

public class HomeSelectViewModel extends ViewModel {

    private final BinnacleRepository binnacleRepository;
    private MultipleDocumentReferenceLiveData<Binnacle, Query> allLiveData; // various objects
    private DocumentReferenceFirebaseLiveData<Binnacle> singleLiveData; // one object

    public HomeSelectViewModel() {
        this.binnacleRepository = BinnacleRepository.getInstance();
    }

    // Returns all documents saved in FireStore
    public MultipleDocumentReferenceLiveData<Binnacle, Query> getAllLiveData() {
        if(allLiveData == null){
            allLiveData = binnacleRepository.findAll();
        }
        return allLiveData;
    }

    // Returns a document by its own id
    public DocumentReferenceFirebaseLiveData<Binnacle> findById(String id){
        if (singleLiveData == null){
            singleLiveData = binnacleRepository.findById(id);
        }
        return singleLiveData;
    }

    // Returns all documents by user id
    public MultipleDocumentReferenceLiveData<Binnacle,Query> getAllByUser(String userId){
        if(allLiveData == null){
            allLiveData = binnacleRepository.finByUser(userId);
        }
        return allLiveData;
    }

    // Save a document in FireStore with a generated Id
    public void saveWithIdGenerated(Binnacle binnacle , String id){
        binnacleRepository.saveWithExistentId(binnacle, id);
    }

    // Save a document in FireStore
    public void save(Binnacle binnacle){
        binnacleRepository.save(binnacle);
    }

    // Delete a document in FireStore
    public void delete(Binnacle binnacle){
        binnacleRepository.delete(binnacle);
    }

    // Update a document in FireStore
    public void update(Binnacle binnacle){
        binnacleRepository.update(binnacle);
    }

}
