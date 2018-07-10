package com.arecmetafora.leilaoreceitafederal.model;

import com.arecmetafora.leilaoreceitafederal.model.to.Portal;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Network API service to access Auction information from remote repository.
 */
public interface ApiService {

    /**
     * @return Gets all home information from remote repository.
     */
    @GET("portal")
    Call<Portal> getHomeData(@QueryMap Map<String, List<String>> filters);
}
