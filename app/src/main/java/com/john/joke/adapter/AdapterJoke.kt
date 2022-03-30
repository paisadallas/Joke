package com.john.joke.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.joke.databinding.ItemJokeBinding
import com.john.joke.model.JokeList
import com.john.joke.model.Value
import com.john.joke.model.ValueList

class AdapterJoke(
   private var jokeList: MutableList<ValueList> = mutableListOf()
) :RecyclerView.Adapter<JokeHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeHolder {
        return  JokeHolder(ItemJokeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: JokeHolder, position: Int) {
        holder.bind(jokeList[position])
    }

    override fun getItemCount(): Int = jokeList.size

    fun update(joke : List<ValueList>){
        jokeList.clear()
        jokeList.addAll(joke)
        notifyDataSetChanged()
    }
}

class JokeHolder(
    private val binding : ItemJokeBinding
):RecyclerView.ViewHolder(binding.root){

    fun bind (jokeItem :ValueList){
        binding.tvJokes.text = jokeItem.joke
    }

}

