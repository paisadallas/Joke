package com.john.joke.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Value(
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("joke")
    val joke: String
)