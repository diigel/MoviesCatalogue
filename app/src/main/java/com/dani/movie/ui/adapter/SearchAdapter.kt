package com.dani.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.data.removeDuplicatesItem
import com.dani.movie.data.entity.SearchDto
import com.dani.movie.databinding.ItemListSearchBinding
import com.dani.movie.ui.holder.SearchViewHolder

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private val dataList : MutableList<SearchDto> = mutableListOf()
    private var click : ((data : SearchDto) -> Unit)? = null

    fun addList(data : List<SearchDto>) {
        dataList.clear()
        dataList.addAll(data)
        dataList.removeDuplicatesItem()
        notifyDataSetChanged()
    }

    fun onClick(click: (data : SearchDto) -> Unit){
        this.click = click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemListSearchBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(dataList[position],click)
    }

    override fun getItemCount(): Int = dataList.size
}