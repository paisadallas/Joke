package com.john.joke.res

import com.john.joke.model.Joke
import com.john.joke.model.JokeList
import com.john.joke.model.Value
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 */
interface JokeAPI {
    //http://api.icndb.com/jokes/random
    @GET(JOKE_PATH)
    suspend fun getRandomJoke(
        @Query("exclude") exclude: List<String>
    ): Response <Joke>

    //http://api.icndb.com/jokes/random?firstName=John&lastName=Doe
    @GET("random")
    suspend fun getCustomJoke(
        @Query("firstName") firstName:String,
        @Query("lastName") lastName:String
    ):Response<Joke>

   // http://api.icndb.com/jokes?exclude=[explicit]
    @GET(ALL_JOKE)
    suspend fun getNoExplicit(
       @Query("exclude") exclude: List<String>
    ): Response<JokeList>


    companion object{

        const val BASE_URL = "http://api.icndb.com/jokes/"
        private const val JOKE_PATH = "random"
        private const val ALL_JOKE ="."

    }
}