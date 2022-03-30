package com.john.joke.model


import com.google.gson.annotations.SerializedName

data class ValueList(
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val joke: String
)