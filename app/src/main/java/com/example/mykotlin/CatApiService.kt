package com.example.mykotlin

import retrofit2.Call
import retrofit2.http.GET


interface CatApiService {
    @GET("api/cats?tags=cute")
    fun getCuteCats(): Call<List<Cat>>
}