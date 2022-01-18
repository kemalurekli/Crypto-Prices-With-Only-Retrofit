package com.kemalurekli.cryptoprices.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kemalurekli.cryptoprices.model.PricesModel
import com.kemalurekli.cryptoprices.service.PriceAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel : ViewModel() {
    val prices = MutableLiveData<List<PricesModel>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val priceAPIService = PriceAPIService()

    fun refreshData() {
        loading.value = true
        priceAPIService.getPriceData().enqueue(object: Callback<List<PricesModel>> {
            override fun onResponse(
                call: Call<List<PricesModel>>,
                response: Response<List<PricesModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        updateLiveData(it)
                        loading.value = false
                        error.value = false
                    }
                }
            }
            override fun onFailure(call: Call<List<PricesModel>>, t: Throwable) {
                t.printStackTrace()
                error.value = true
            }
        })
    }

    fun updateLiveData(list: List<PricesModel>){
        prices.value = list
        println(list.size)
    }

}

