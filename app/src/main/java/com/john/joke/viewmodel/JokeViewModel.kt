package com.john.joke.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.joke.database.DatabaseRepository
import com.john.joke.model.JokeList
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

    var explicit = listOf<String>("")
    private val _sortedJoke: MutableLiveData<JokeState> = MutableLiveData(JokeState.LOADING)
    private val _sortedList: MutableLiveData<JokeState> = MutableLiveData(JokeState.LOADING)
     val jokes: LiveData<JokeState> get() = _sortedJoke
     val jokeList : LiveData<JokeState> get() = _sortedList
    fun getRandomJoke(explicit: List<String>) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokeNetwork.getRandomJoke(explicit)
                if (response.isSuccessful) {
                    response.body()?.let {

                        var myObject : Value? = it.value
                        _sortedJoke.postValue(JokeState.SUCCESS(myObject))

                    }?: throw Exception("Response null")
                } else {
                    throw Exception("No successful response")
                }

            } catch (e: Exception) {
                _sortedJoke.postValue(JokeState.ERROR(e))
            }
        }
    }

    fun getCustomJoke(name:String,lastName:String){
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokeNetwork.getCustomJoke(name, lastName)
                if (response.isSuccessful) {
                    response.body()?.let {
                        var myObject: Value ?= it.value
                        _sortedJoke.postValue(JokeState.SUCCESS(myObject))
                    }
                }
            } catch (e: java.lang.Exception) {
                _sortedJoke.postValue(JokeState.ERROR(e))
            }
        }
    }

    fun getNoExplicit(explicit: List<String>) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokeNetwork.getNoExplicit(explicit)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _sortedList?.postValue(JokeState.SUCCESS(it))

                        var listJoke2  = it as JokeList

                      //  loadData(listJoke2.value)
                    }
                }
            } catch (e: java.lang.Exception) {
                _sortedJoke.postValue(JokeState.ERROR(e))
            }
        }
    }

    suspend fun loadData(data:List<Value>){

        try {
            val localData = databaseRepo.insertJoke(data)
            _sortedList.postValue(JokeState.SUCCESS(localData))

        }catch (e :Exception){
            _sortedList.postValue(JokeState.ERROR(e))
        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}