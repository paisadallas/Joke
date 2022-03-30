package com.john.joke.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.joke.R
import com.john.joke.adapter.AdapterJoke
import com.john.joke.databinding.FragmentJokesBinding
import com.john.joke.model.JokeList
import com.john.joke.model.ValueList
import com.john.joke.utils.JokeState


class JokesFragment : BaseFragment() {

    private val binding by lazy {
        FragmentJokesBinding.inflate(layoutInflater)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.rvJokes.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = adapterJoke
        }

       // jokeViewModel.getAllJokes()
        jokeViewModel.getNoExplicit(jokeViewModel.explicit)
        jokeViewModel.jokes.observe(viewLifecycleOwner){
            when(it){
                is JokeState.LOADING ->{

                }
                is JokeState.SUCCESS <*>->{

                    var listJoke2  = it.joke as JokeList

                    adapterJoke.update(listJoke2.value)

                }
                is JokeState.ERROR ->{
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG).show()
                    Log.d("RESPOND",it.error.localizedMessage)
                }
            }
        }

        return binding.root
    }


}