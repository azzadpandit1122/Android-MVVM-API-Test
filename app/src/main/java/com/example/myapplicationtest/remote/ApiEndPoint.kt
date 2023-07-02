package com.example.myapplicationtest.remote

import com.example.myapplicationtest.response.GetNameInfoResponses
import com.example.myapplicationtest.response.UserData
import retrofit2.http.*

interface ApiEndPoint {

    @GET("api/character")
    suspend fun getUserData(): UserData

//    https://rickandmortyapi.com/api/character/2

    @GET("api/character/{id}")
    suspend fun getNameInformation(@Path("id") name : String): GetNameInfoResponses


}
