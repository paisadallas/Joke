package com.john.joke.model


import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: List<Value>
)