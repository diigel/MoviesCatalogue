package com.dani.favorites.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.dani.data.Network
import com.dani.data.loadImage
import com.dani.favorites.data.entity.FavoriteEntity
import com.dani.favorites.databinding.ItemListFavoriteBinding

class FavoriteViewHolder(private val binding: ItemListFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: FavoriteEntity,
        click : ((click : FavoriteEntity) -> Unit)? = null
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