package com.example.tourguide.provider_ui.providerHome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProviderHomeViewModel extends ViewModel {

    private final MutableLiveData<String> myText;

    public ProviderHomeViewModel() {
        myText = new MutableLiveData<>();
        myText.setValue("Profile Settings");
    }

    public LiveData<String> getText() {
        return myText;
    }
}