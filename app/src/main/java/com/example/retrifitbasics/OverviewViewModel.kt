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

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private val _property = MutableLiveData<MarsProperty>()
    val property: LiveData<MarsProperty>
        get() = _property


    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            try {
                _properties.value = MarsApi.retrofitService.getProperties()
                _response.value = "Success: ${_properties.value!!.size} Mars properties retrieved."

                if (!_properties.value.isNullOrEmpty()) {
                    _property.value = _properties.value!![1]
                    Log.d("OverviewViewModel", "_property.value: ${_property.value}")
                }
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}