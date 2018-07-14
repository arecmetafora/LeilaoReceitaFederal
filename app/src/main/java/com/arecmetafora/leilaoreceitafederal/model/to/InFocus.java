package com.arecmetafora.leilaoreceitafederal.model.to;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class InFocus extends Auction implements Serializable {

    @SerializedName("dataFimProposta")
    public Date bidEndDate;

    @SerializedName("imagemDestaque")
    public String lotImage;

    @SerializedName("numero")
    public int lotNumber;

    @SerializedName("lote")
    public int lotSize;

    @SerializedName("valor")
    public double lotValue;
}
