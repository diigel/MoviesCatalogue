package com.dani.movies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.data.removeDuplicatesItem
import com.dani.movies.data.entity.MoviesDto
import com.dani.movies.databinding.ItemListMoviesBinding
import com.dani.movies.ui.viewholder.MovieViewHolder

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val dataList : MutableList<MoviesDto> = mutableListOf()
    private var click : ((data : MoviesDto) -> Unit)? = null

    fun onClick(click: (data : MoviesDto) -> Unit) {
        this.click = click
    }

    fun addList(data : List<MoviesDto>){
        dataList.clear()
        dataList.addAll(data)
        dataList.removeDuplicatesItem()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemListMoviesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindHolder(dataList[position],click)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

}