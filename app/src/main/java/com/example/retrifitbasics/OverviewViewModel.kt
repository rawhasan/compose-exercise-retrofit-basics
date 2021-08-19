package com.example.retrifitbasics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrifitbasics.network.MarsApi
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getProperties()
                _response.value = "Success: ${listResult.size} Mars properties retrieved."
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}