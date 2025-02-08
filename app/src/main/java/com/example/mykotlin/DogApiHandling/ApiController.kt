package com.example.mykotlin.DogApiHandling

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiController {
    private const val BASE_URL = "https://dogapi.dog"

    val apiServices: DogApiServices by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiServices::class.java)
    }
}
