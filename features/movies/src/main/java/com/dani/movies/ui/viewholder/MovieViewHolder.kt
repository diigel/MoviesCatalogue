package com.dani.movies.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.dani.data.Network
import com.dani.data.loadImage
import com.dani.movies.data.entity.MoviesDto
import com.dani.movies.databinding.ItemListMoviesBinding

class MovieViewHolder(private val binding: ItemListMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindHolder(
        data: MoviesDto,
        click : ((data : MoviesDto) -> Unit)?
    ) = binding.run {
        txtTitle.text = data.originalTitle
        txtRelease.text = data.releaseDate
        txtDescription.text = data.overview
        txtRating.text = data.voteAverage.toString()
        imgPosterPath.loadImage(Network.IMG_URL+data.posterPath)
        imgBackdropPath.loadImage(Network.IMG_URL+data.backdropPath)
        itemView.setOnClickListener {
            click?.invoke(data)
        }
    }
}