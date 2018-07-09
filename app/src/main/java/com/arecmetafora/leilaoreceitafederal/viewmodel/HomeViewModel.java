package com.arecmetafora.leilaoreceitafederal.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.arecmetafora.leilaoreceitafederal.di.CustomApplication;
import com.arecmetafora.leilaoreceitafederal.model.ApiService;
import com.arecmetafora.leilaoreceitafederal.model.to.Portal;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends BaseViewModel {

    @Inject
    ApiService mApiService;

    private MutableLiveData<Portal> mData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        ((CustomApplication)application).getAppComponent().inject(this);
    }

    /**
     * @return Loads all home information from remote repository.
     */
    public LiveData<Portal> getData() {
        if(mData == null) {
            mData = new MutableLiveData<>();
            refreshData();
        }
        return mData;
    }

    /**
     * Load data information from remote service.
     */
    private void refreshData() {
        mApiService.getHomeData().enqueue(new Callback<Portal>() {
            @Override
            public void onResponse(@NonNull Call<Portal> call, @NonNull Response<Portal> response) {
                Portal data = response.body();
                if(data != null) {
                    mData.postValue(data);
                }
            }
            @Override
            public void onFailure(@NonNull Call<Portal> call, @NonNull Throwable t) {
                // Infinity retry while load fails (TODO: improve retry mechanism)
                refreshData();
            }
        });
    }
}
