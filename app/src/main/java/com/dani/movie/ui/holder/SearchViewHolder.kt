package com.dani.movie.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.dani.data.Network
import com.dani.data.loadImage
import com.dani.movie.data.entity.SearchDto
import com.dani.movie.databinding.ItemListSearchBinding

class SearchViewHolder(private val binding: ItemListSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        data: SearchDto,
        click: ((data: SearchDto) -> Unit)?
    ) = binding.run {
        txtTitle.text = data.originalTitle
        txtRelease.text = data.releaseDate
        txtDescription.text = data.overview
        imgPosterPath.loadImage(Network.IMG_URL + data.posterPath)
        imgBackdropPath.loadImage(Network.IMG_URL + data.backdropPath)
        itemView.setOnClickListener {
            click?.invoke(data)
        }
    }
}