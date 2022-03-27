package com.john.joke.res

import com.john.joke.model.Value
import retrofit2.Response


interface JokeRepository {
    suspend fun getRandomJoke(): Response<List<Value>>
}

class JokeRepositoryImpl(
    private val jokeApi: JokeAPI
) : JokeRepository {
    override suspend fun getRandomJoke(): Response<List<Value>> =
        jokeApi.getRandomJoke()
}