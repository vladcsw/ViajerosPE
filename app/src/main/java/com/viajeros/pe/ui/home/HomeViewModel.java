package com.viajeros.pe.ui.home;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.viajeros.pe.firebase.livedata.DocumentReferenceFirebaseLiveData;
import com.viajeros.pe.firebase.livedata.MultipleDocumentReferenceLiveData;
import com.viajeros.pe.firebase.model.Binnacle;
import com.viajeros.pe.firebase.model.ToDoList;
import com.viajeros.pe.firebase.model.TouristPlace;
import com.viajeros.pe.firebase.repository.BinnacleRepository;
import com.viajeros.pe.firebase.repository.ToDoListRepository;
import com.viajeros.pe.firebase.repository.TouristPlaceRepository;

public class HomeViewModel extends ViewModel {

    private final TouristPlaceRepository touristPlaceRepository;
    private final BinnacleRepository binnacleRepository;
    private final ToDoListRepository toDoListRepository;
    private MultipleDocumentReferenceLiveData<TouristPlace, Query> allLiveData;
    private DocumentReferenceFirebaseLiveData<Binnacle> allLiveDataBinnacle;
    private DocumentReferenceFirebaseLiveData<ToDoList> allLiveDataToDoList;

    public HomeViewModel(){

        this.touristPlaceRepository = TouristPlaceRepository.getInstance();
        this.binnacleRepository = BinnacleRepository.getInstance();
        this.toDoListRepository = ToDoListRepository.getInstance();
    }

    public MultipleDocumentReferenceLiveData<TouristPlace, Query> getAllLiveData() {
        if (allLiveData == null) {
            allLiveData = touristPlaceRepository.findAll();
        }
        return allLiveData;
    }
    public DocumentReferenceFirebaseLiveData<Binnacle> getLiveDataBinnacle(String idBinnacle){
        if (allLiveDataBinnacle == null) {
            allLiveDataBinnacle = binnacleRepository.findById(idBinnacle);
        }
        return allLiveDataBinnacle;
    }

    public DocumentReferenceFirebaseLiveData<ToDoList> getLiveDataToDo(String idBinnacle){
        if (allLiveDataToDoList == null) {
            allLiveDataToDoList = toDoListRepository.findById(idBinnacle);
        }
        return allLiveDataToDoList;
    }


}