package com.john.joke.database

import com.john.joke.model.Value


interface DatabaseRepository{
    suspend fun insertJoke(newJoke:List<Value>)
    suspend fun getAllJoke():List<Value>
    suspend fun deleteJokes(jokes:List<Value>)
}


class DatabaseRepositoryImpl (
    private val jokeDatabase: JokeDao
        ): DatabaseRepository{
    override suspend fun insertJoke(newJoke: List<Value>) =
        jokeDatabase.insertJoke(newJoke)

    override suspend fun getAllJoke(): List<Value> {
      return jokeDatabase.getAllJoke()
    }

    override suspend fun deleteJokes(jokes: List<Value>) =
        jokeDatabase.deleteJokes(jokes)
}