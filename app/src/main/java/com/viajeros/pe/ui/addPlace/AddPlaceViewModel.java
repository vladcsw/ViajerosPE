package com.viajeros.pe.ui.addPlace;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddPlaceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddPlaceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add place fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}