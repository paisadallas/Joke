package com.john.joke.res

import com.john.joke.model.Joke
import com.john.joke.model.JokeList
import retrofit2.Response


interface JokeRepository {
    suspend fun getRandomJoke(explicit: List<String>): Response <Joke>
    suspend fun getCustomJoke(name:String,lastName:String):Response <Joke>
    suspend fun getNoExplicit(explicit: List<String>) : Response <JokeList>
}

class JokeRepositoryImpl(
    private val jokeApi: JokeAPI
) : JokeRepository {
    override suspend fun getRandomJoke(explicit: List<String>): Response <Joke> =
        jokeApi.getRandomJoke(explicit)

    override suspend fun getCustomJoke(name:String,lastName: String): Response<Joke> =
        jokeApi.getCustomJoke(name,lastName)

    override suspend fun getNoExplicit(explicit: List<String>): Response<JokeList> =
        jokeApi.getNoExplicit(explicit)


}