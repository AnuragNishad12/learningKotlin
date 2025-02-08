package com.example.mykotlin.DogApiHandling

import com.google.gson.annotations.SerializedName


data class DogFactsModel(
    @SerializedName("facts")
    val facts: List<String>,

    @SerializedName("success")
    val success: Boolean
)
