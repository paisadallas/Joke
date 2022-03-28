package com.john.joke.model


import com.google.gson.annotations.SerializedName

/**
 * Data class model Joke
 * API http://api.icndb.com/jokes/random
 */

data class Joke(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: Value
)