package com.example.myapplicationtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationtest.remote.NetworkResult
import com.example.myapplicationtest.repository.Repository
import com.example.myapplicationtest.response.GetNameInfoResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository = Repository()) : ViewModel() {

    var getUserData = MutableLiveData<NetworkResult<GetNameInfoResponses>>()

    fun getNameInfoResponse(name:String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNameDetails(name).collectLatest {
                getUserData.postValue(it)
            }
        }
    }

}