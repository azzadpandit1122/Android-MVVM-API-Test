package com.example.myapplicationtest.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.myapplicationtest.remote.ApiEndPoint
import com.example.myapplicationtest.remote.NetworkResult
import com.example.myapplicationtest.remote.RetrofitClient
import com.example.myapplicationtest.response.UserData
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class Repository(private val client: ApiEndPoint = RetrofitClient.getInstance()) {

    suspend fun getRegister() = flow {
        emit(NetworkResult.Loading())
        val response = client.getUserData()
        emit(NetworkResult.Success(response))
    }.catch { e ->
        when (e) {
            is HttpException -> {
                if (e.code() == 500) {
                    emit(NetworkResult.Error("something went wrong ?"))
                } else if (e.code() == 404) {
                    emit(NetworkResult.Error("Url not found"))
                } else if (e.code() == 401) {
                    val respose = Gson().fromJson(
                        e.response()?.errorBody()?.charStream(),
                        UserData::class.java
                    )
                    emit(NetworkResult.Error(e.message))
                } else {
                    emit(NetworkResult.Error(e.message))
                }
            }
            is NetworkErrorException -> {
                emit(NetworkResult.Error(e.message))
            }
        }
    }


    suspend fun getNameDetails(name: String) = flow {
        Log.e("TAG", "getNameDetails: "+name.toString() )
        emit(NetworkResult.Loading())
        val response = client.getNameInformation(name)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        when (e) {
            is HttpException -> {
                if (e.code() == 500) {
                    emit(NetworkResult.Error("something went wrong ?"))
                } else if (e.code() == 404) {
                    emit(NetworkResult.Error("Url not found"))
                } else if (e.code() == 401) {
                    val respose = Gson().fromJson(
                        e.response()?.errorBody()?.charStream(),
                        UserData::class.java
                    )
                    emit(NetworkResult.Error(e.message))
                } else {
                    emit(NetworkResult.Error(e.message))
                }
            }
            is NetworkErrorException -> {
                emit(NetworkResult.Error(e.message))
            }
        }
    }

}