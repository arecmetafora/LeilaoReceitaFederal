package com.arecmetafora.leilaoreceitafederal.model.to;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Auction {

    @SerializedName("edital")
    public String number;

    public String edle;

    @SerializedName("permitePF")
    public boolean allowOrdinaryPeopleToParticipate;

    @SerializedName("tipo")
    public String type;

    @SerializedName("orgao")
    public String organ;

    @SerializedName("cidade")
    public String city;

    @SerializedName("dataInicioPropostas")
    public Date bidStartDate;

    @SerializedName("dataFimPropostas")
    public Date bidEndDate;

    @SerializedName("dataAberturaLances")
    public Date bidOpeningDate;

    @SerializedName("lotes")
    public int lotSize;
}
