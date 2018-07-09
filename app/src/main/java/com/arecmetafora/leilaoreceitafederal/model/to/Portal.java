package com.arecmetafora.leilaoreceitafederal.model.to;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Portal {

    @SerializedName("agora")
    public Date time;

    @SerializedName("filtros")
    public Filter[] filters;

    //@SerializedName("destaques")
    //InFocus[] inFocuses;

    @SerializedName("situacoes")
    public AuctionEvent[] auctionEvents;

    @SerializedName("editais")
    public Notice[] noticies;
}
