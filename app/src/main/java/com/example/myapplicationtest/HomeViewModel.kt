package com.example.myapplicationtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationtest.remote.NetworkResult
import com.example.myapplicationtest.repository.Repository
import com.example.myapplicationtest.response.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository = Repository()) : ViewModel() {

    var getUserData = MutableLiveData<NetworkResult<UserData>>()

    fun getUserDataResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRegister(/*registerRequestModel*/).collectLatest {
                getUserData.postValue(it)
            }
        }
    }
}