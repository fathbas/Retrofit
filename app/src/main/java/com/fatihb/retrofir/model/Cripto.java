package com.fatihb.retrofir.model;

import com.google.gson.annotations.SerializedName;

public class Cripto {

    @SerializedName("currency")
    public String currency;
    @SerializedName("price")
    public String price;
}
