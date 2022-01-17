package com.kemalurekli.cryptoprices.service


import com.kemalurekli.cryptoprices.model.PricesModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PriceAPIService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    val api = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build().create(PriceAPI::class.java)

    fun getPriceData() : Call<List<PricesModel>>{
        return api.getPrice()
    }

}