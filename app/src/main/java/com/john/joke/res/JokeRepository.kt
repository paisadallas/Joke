package com.john.joke.res

import com.john.joke.model.Joke
import com.john.joke.model.JokeList
import retrofit2.Response


interface JokeRepository {
    suspend fun getRandomJoke(): Response <Joke>
    suspend fun getCustomJoke(name:String,lastName:String):Response <Joke>
    suspend fun getAllJoke() : Response <JokeList>
    suspend fun getNoExplicit(explicit: List<String>) : Response <JokeList>
}

class JokeRepositoryImpl(
    private val jokeApi: JokeAPI
) : JokeRepository {
    override suspend fun getRandomJoke(): Response <Joke> =
        jokeApi.getRandomJoke()

    override suspend fun getCustomJoke(name:String,lastName: String): Response<Joke> =
        jokeApi.getCustomJoke(name,lastName)

    override suspend fun getAllJoke(): Response <JokeList> =
        jokeApi.getAllJoke()

    override suspend fun getNoExplicit(explicit: List<String>): Response<JokeList> =
        jokeApi.getNoExplicit(explicit)


}