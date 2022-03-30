package com.john.joke.view

import androidx.fragment.app.Fragment
import com.john.joke.adapter.AdapterJoke
import com.john.joke.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BaseFragment: Fragment() {
    protected val jokeViewModel: JokeViewModel by sharedViewModel()

    protected val adapterJoke by lazy {
        AdapterJoke()
    }



}