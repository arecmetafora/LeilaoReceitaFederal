package com.arecmetafora.leilaoreceitafederal.model.to;

import com.google.gson.annotations.SerializedName;

public class AuctionEvent {

    @SerializedName("codigoSituacao")
    public int id;

    public String label;

    @SerializedName("lista")
    public Auction[] auctions;
}
