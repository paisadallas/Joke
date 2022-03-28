package com.john.joke.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.joke.database.DatabaseRepository
import com.john.joke.model.Value
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
     val jokes: LiveData<JokeState> get() = _sortedJoke

    fun getAllJoke() {
        viewModelScope.launch(ioDispatcher) {
            try {
                Log.d("RESPONSE","1")
                val response = jokeNetwork.getRandomJoke()
                Log.d("RESPONSE","2")
                if (response.isSuccessful) {
                    Log.d("RESPONSE","3")
                    response.body()?.let {
                        Log.d("RESPONSE","4")
                        Log.d("RESPONSE",it.toString())
                        Log.d("RESPONSE","5")
                        Log.d("RESPONSE",it.value.joke)
                     //   var dataJoke = databaseRepo.insertJoke(it)
                        var myObject : Value = it.value
                        _sortedJoke.postValue(JokeState.SUCCESS(myObject))
                      //  databaseRepo.insertJoke(it)
                     //   Log.d("RESPONSE",it[0].toString())
                     //   val localData = databaseRepo.getAllJoke()
                     //   _sortedJoke.postValue(JokeState.SUCCESS(localData))
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