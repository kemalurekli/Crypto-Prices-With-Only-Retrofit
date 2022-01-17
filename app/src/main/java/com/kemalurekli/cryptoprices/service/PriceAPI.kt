package com.kemalurekli.cryptoprices.service

import com.kemalurekli.cryptoprices.model.PricesModel
import retrofit2.Call
import retrofit2.http.GET

interface PriceAPI {
    @GET("kemalurekli/Crypto-Prices-With-Retrofit-Rxjava/main/myData.json")
    fun getPrice() : Call<List<PricesModel>>
}