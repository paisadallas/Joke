package com.john.joke.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.joke.database.DatabaseRepository
import com.john.joke.res.JokeRepository
import com.john.joke.utils.JokeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeViewModel(
    private val jokeNetwork: JokeRepository,
    private val databaseRepo: DatabaseRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _sortedJoke: MutableLiveData<JokeState> = MutableLiveData(JokeState.LOADING)
    private val jokes: LiveData<JokeState> get() = _sortedJoke

    fun getAllJoke() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokeNetwork.getRandomJoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        databaseRepo.insertJoke(it)
                        val localData = databaseRepo.getAllJoke()
                        _sortedJoke.postValue(JokeState.SUCCESS(localData))
                    }?: throw Exception("Response null")
                } else {
                    throw Exception("No successful response")
                }

            } catch (e: Exception) {
                _sortedJoke.postValue(JokeState.ERROR(e))
            }
        }
    }

}