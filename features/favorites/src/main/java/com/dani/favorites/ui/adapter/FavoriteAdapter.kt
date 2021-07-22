package com.dani.favorites.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.data.removeDuplicatesItem
import com.dani.favorites.data.entity.FavoriteEntity
import com.dani.favorites.databinding.ItemListFavoriteBinding
import com.dani.favorites.ui.viewholder.FavoriteViewHolder

class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>(){
    private val dataList : MutableList<FavoriteEntity> = mutableListOf()

    private var click : ((data : FavoriteEntity) -> Unit)? = null

    fun onClickFavorite(click: (data : FavoriteEntity)-> Unit){
        this.click = click
    }
    fun addList(data : List<FavoriteEntity>){
        dataList.clear()
        dataList.addAll(data)
        dataList.removeDuplicatesItem()
        notifyDataSetChanged()
    }
    fun removeById(movieId : Int){
        dataList.removeDuplicatesItem()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(dataList[position],click)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}