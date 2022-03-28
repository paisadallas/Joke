package com.john.joke.utils

sealed class JokeState {
    object LOADING: JokeState()
    class SUCCESS<T>(val joke: T):JokeState()
    class ERROR(val error:Throwable) : JokeState()
}