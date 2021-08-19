package com.example.retrifitbasics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrifitbasics.network.MarsApi
import com.example.retrifitbasics.network.MarsProperty
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _property = MutableLiveData<MarsProperty>()
    val property: LiveData<MarsProperty>
        get() = _property


    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getProperties()
                _response.value = "Success: ${listResult.size} Mars properties retrieved."

                if (listResult.isNotEmpty()) {
                    _property.value = listResult[1]
                    Log.d("OverviewViewModel", "_property.value: ${_property.value}")
                }
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}