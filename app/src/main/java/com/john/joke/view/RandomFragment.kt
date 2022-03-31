package com.john.joke.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.john.joke.databinding.FragmentRandomBinding
import com.john.joke.model.JokeList
import com.john.joke.model.Value
import com.john.joke.utils.JokeState

class RandomFragment : BaseFragment() {

    private val binding by lazy {
        FragmentRandomBinding.inflate(layoutInflater)
    }

    private var message = ""
    private var dataEmit = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.cbExplicit.setOnClickListener {
            if (binding.cbExplicit.isChecked){
                Log.d("CHECKED","ENABLE")
               jokeViewModel.explicit = listOf("")
            }
            else{
                Log.d("CHECKED","DISABLE")
                jokeViewModel.explicit = listOf("explicit")
            }
        }

        binding.btGenerate.setOnClickListener {
            jokeViewModel.getRandomJoke(jokeViewModel.explicit)
            readData()
        }

        //Read data

        binding.btLoadData.setOnClickListener {

              var miData =  jokeViewModel.jokeList as JokeList
              Log.d("DATA_LIFE", miData.toString())

        }







        return binding.root
    }

    private fun message() {

        AlertDialog.Builder(requireContext())
            .setTitle("Joke")
            .setMessage(message)
            .setPositiveButton("ok") { dialogo, i ->
                dialogo.cancel()
            }
            .create()
            .show()

        dataEmit =0
    }

    private fun readData() {
        jokeViewModel.jokes.observe(viewLifecycleOwner) {
            when (it) {
                is JokeState.LOADING -> {
                    Toast.makeText(requireContext(), "loading...", Toast.LENGTH_LONG).show()
                    binding.btGenerate.isEnabled = false
                }
                is JokeState.SUCCESS<*> -> {
                    binding.btGenerate.isEnabled = true
                    var miObject: Value = it.joke as Value
                    message = miObject.joke
                    Log.d("RESPONSE","randomFragment $message")
                    dataEmit +=1
                    if (dataEmit==2){
                        message()
                    }
                }
                is JokeState.ERROR -> {
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }


    }

