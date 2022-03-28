package com.john.joke.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.john.joke.database.Converters

/**
 * Data class Value, this class receive the data to be show
 */

@Entity
@TypeConverters(Converters::class)
data class Value(
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("joke")
    val joke: String
)