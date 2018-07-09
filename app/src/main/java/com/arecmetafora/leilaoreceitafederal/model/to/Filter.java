package com.arecmetafora.leilaoreceitafederal.model.to;

import com.google.gson.annotations.SerializedName;

public class Filter {

    public String id;
    public String label;

    @SerializedName("opcoes")
    public Options[] options;

    public static class Options {
        public String id;
        public String label;

        @SerializedName("contador")
        public int count;
    }
}
