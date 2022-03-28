package com.john.joke.res

import com.john.joke.model.Joke
import retrofit2.Response


interface JokeRepository {
    suspend fun getRandomJoke(): Response <Joke>
}

class JokeRepositoryImpl(
    private val jokeApi: JokeAPI
) : JokeRepository {
    override suspend fun getRandomJoke(): Response <Joke> =
        jokeApi.getRandomJoke()
}