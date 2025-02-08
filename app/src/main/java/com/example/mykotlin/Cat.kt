package com.example.mykotlin

import com.google.gson.annotations.SerializedName

data class Cat(
    val _id: String,
    val mimetype: String,
    val size: Int,
    val tags: List<String>
)



