package com.example.mykotlin.DogApiHandling

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//interface DogApiServices {
//    @GET("/api/facts?number=5")
//    fun getDogFacts() : DogFactsModel
//}

interface DogApiServices {
    @GET("/api/facts")
    suspend fun getDogFacts(@Query("number") number: Int): Response<DogFactsModel>
}