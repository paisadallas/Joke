package com.john.joke.utils

import com.john.joke.model.Value

sealed class JokeState {
    object LOADING: JokeState()
    class SUCCESS<T>(val joke: T):JokeState()
    class ERROR(val error:Throwable) : JokeState()
}