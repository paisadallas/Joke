package com.john.joke.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.john.joke.R
import com.john.joke.databinding.FragmentCustomBinding
import com.john.joke.model.Value
import com.john.joke.utils.JokeState

class CustomFragment : BaseFragment() {

    private val binding by lazy {
        FragmentCustomBinding.inflate(layoutInflater)
    }
    var dataEmit =0
    var message = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btGenerate.setOnClickListener {
            jokeViewModel.getCustomJoke(binding.etName.text.toString(),binding.etLastName.text.toString())

            readData()
        }

        Log.d("CHECKED","CHEKED = ${jokeViewModel.explicit}")

        return binding.root
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
                    dataEmit +=1
                    Log.d("RESPONSE","customFragment $message ${dataEmit}")
                    if (dataEmit ==2){
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

    private fun message() {


        Log.d("RESPONSE","SHOW_MESSAGE")
            AlertDialog.Builder(requireContext())
                .setTitle("Joke")
                .setMessage(message)
                .setPositiveButton("ok") { dialogo, i ->
                    dialogo.cancel()
                }
                .create()
                .show()
        dataEmit=0
    }



}