package com.example.mykotlin.DogApiHandling

class DogFactsRepository {
    private val api = ApiController.apiServices

    suspend fun getDogFacts(number: Int): DogFactsModel? {
        val response = api.getDogFacts(number)
        return if (response.isSuccessful) response.body() else null





    }
}