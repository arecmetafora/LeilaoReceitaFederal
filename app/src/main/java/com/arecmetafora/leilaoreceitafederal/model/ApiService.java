package com.arecmetafora.leilaoreceitafederal.model;

import com.arecmetafora.leilaoreceitafederal.model.to.Portal;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Network API service to access Auction information from remote repository.
 */
public interface ApiService {

    /**
     * @return Gets all home information from remote repository.
     */
    @GET("portal")
    Call<Portal> getHomeData();
}
