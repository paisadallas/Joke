package com.john.joke.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.joke.R
import com.john.joke.databinding.FragmentCustomBinding

class CustomFragment : Fragment() {

    private val binding by lazy {
        FragmentCustomBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

}