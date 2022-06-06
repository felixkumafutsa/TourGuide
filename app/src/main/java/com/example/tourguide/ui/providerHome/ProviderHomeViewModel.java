package com.example.tourguide.ui.providerHome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProviderHomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ProviderHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Profile Settings");
    }

    public LiveData<String> getText() {
        return mText;
    }
}