package com.john.joke.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.john.joke.model.Value

@Database(
    entities = [Value::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class JokeDatabase: RoomDatabase() {
    abstract fun getJokeDao():JokeDao
}

@Dao
interface JokeDao{

    @Insert(onConflict = REPLACE)
    suspend fun insertJoke(newJoke: List<Value>)

    @Query("SELECT * FROM value")
    suspend fun getAllJoke():List<Value>

    @Delete
    suspend fun deleteJokes(joke:List<Value>)

}