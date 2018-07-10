package com.arecmetafora.leilaoreceitafederal.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.Html;

import com.arecmetafora.leilaoreceitafederal.di.CustomApplication;
import com.arecmetafora.leilaoreceitafederal.model.ApiService;
import com.arecmetafora.leilaoreceitafederal.model.to.Filter;
import com.arecmetafora.leilaoreceitafederal.model.to.Portal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends BaseViewModel {

    @Inject
    ApiService mApiService;

    private MutableLiveData<Portal> mData;
    private Map<String, List<String>> mFilters = new HashMap<>();
    private boolean mFilterChanged = false;

    /**
     * Creates a new view-model entity for home screen.
     * @param application The Android application reference.
     */
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
        mApiService.getHomeData(mFilters).enqueue(new Callback<Portal>() {
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

    /**
     * Applies the filter, refreshing the screen data.
     */
    public void applyFilters() {
        if(mFilterChanged) {
            mData.setValue(null);
            mFilterChanged = false;
            refreshData();
        }
    }

    /**
     * Performs a search from home interface.
     * @param query
     */
    public void search(String query) {
        mFilters.clear();
        if(query != null) {
            List<String> filterValues = new LinkedList<>();
            filterValues.add(query);
            mFilters.put("q", filterValues);
        }
        mFilterChanged = true;
        applyFilters();
    }

    /**
     * Checks if a filter option is selected.
     * @param filter The filter to be checked.
     * @param filterOption The filter option to be checked.
     * @return True if the filter option was selected, or False otherwise.
     */
    public boolean isFilterSelected(Filter filter, Filter.Option filterOption) {
        List<String> filterValues = mFilters.get(filter.id);
        return filterValues != null && filterValues.contains(filterOption.id);
    }

    /**
     * Selects/Unselects a filter option from a filter,
     * @param filter A filter to be selected.
     * @param filterOption A filter option to be selected.
     * @param isChecked Whether the filter option is selected or unselected.
     */
    public void selectFilter(Filter filter, Filter.Option filterOption, boolean isChecked) {
        mFilterChanged = true;
        List<String> filterValues = mFilters.get(filter.id);
        if(isChecked) {
            if(filterValues == null) {
                filterValues = new LinkedList<>();
            }
            filterValues.add(filterOption.id);
            mFilters.put(filter.id, filterValues);
        } else {
            if(filterValues != null && filterValues.size() > 0) {
                filterValues.remove(filterOption.id);
            }
            if(filterValues == null || filterValues.size() == 0) {
                mFilters.remove(filter.id);
            }
        }
    }
}
