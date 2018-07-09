package com.arecmetafora.leilaoreceitafederal.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class BaseViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMessenger = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMessenger() {
        return mMessenger;
    }

    protected void sendMessage(String message) {
        mMessenger.postValue(message);
    }
}
