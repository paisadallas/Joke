package com.john.joke.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.john.joke.R
import com.john.joke.databinding.FragmentRandomBinding
import com.john.joke.model.Value
import com.john.joke.utils.JokeState

class RandomFragment : BaseFragment() {

    private val binding by lazy {
        FragmentRandomBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        jokeViewModel.jokes.observe(viewLifecycleOwner){
            when (it){
                is JokeState.LOADING ->{
                    Toast.makeText(requireContext(), "loading...", Toast.LENGTH_LONG).show()
                }
                is JokeState.SUCCESS <*> ->{
                    val joke = it.joke as List<Value>

                }
                is JokeState.ERROR ->{
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        jokeViewModel.getAllJoke()

        return binding.root
    }

}