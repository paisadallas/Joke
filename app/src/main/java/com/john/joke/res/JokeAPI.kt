package com.john.joke.res

import com.john.joke.model.Joke
import retrofit2.Response
import retrofit2.http.GET

/**
 *
 */
interface JokeAPI {

    @GET(JOKE_PATH)
    suspend fun getRandomJoke(): Response <Joke>

    //http://api.icndb.com/jokes/random
    companion object{
        const val BASE_URL = "http://api.icndb.com/jokes/"
        private const val JOKE_PATH = "random"
    }
}