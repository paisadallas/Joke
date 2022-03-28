package com.john.joke.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.john.joke.databinding.FragmentRandomBinding
import com.john.joke.model.Value
import com.john.joke.utils.JokeState

class RandomFragment : BaseFragment() {

    private val binding by lazy {
        FragmentRandomBinding.inflate(layoutInflater)
    }

    private var message = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        jokeViewModel.getAllJoke()
        readData()

        binding.btGenerate.setOnClickListener {
            message()
        }
        return binding.root
    }

    fun message() {
        jokeViewModel.getAllJoke()
        readData()
        AlertDialog.Builder(requireContext())
            .setTitle("Joke")
            .setMessage(message)
            .setPositiveButton("ok") { dialogo, i ->
                dialogo.cancel()
            }
            .create()
            .show()
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
                }
                is JokeState.ERROR -> {
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }


    }

