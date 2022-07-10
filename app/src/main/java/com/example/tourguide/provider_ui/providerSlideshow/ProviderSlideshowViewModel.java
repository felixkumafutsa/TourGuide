package com.example.tourguide.provider_ui.providerSlideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProviderSlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ProviderSlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}