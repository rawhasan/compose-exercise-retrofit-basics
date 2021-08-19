package com.example.retrifitbasics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrifitbasics.network.MarsApi
import com.example.retrifitbasics.network.MarsProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OverviewViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        MarsApi.retrofitService.getProperties().enqueue(

            object: Callback<List<MarsProperty>> {
                override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
                    _response.value = "Success: ${response.body()?.size} Mars properties retrieved"
                }

                override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

            }

        )
    }
}