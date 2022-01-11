package com.kemalurekli.cryptoprices.model

import com.google.gson.annotations.SerializedName

data class PricesModel (
    @SerializedName("currency")
    val currency : String,
    @SerializedName("price")
    val price : String
    )