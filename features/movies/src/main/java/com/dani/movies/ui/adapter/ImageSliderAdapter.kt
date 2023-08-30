package com.dani.movies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.data.Network
import com.dani.data.loadImage
import com.dani.data.removeDuplicatesItem
import com.dani.movies.data.entity.UpComingDto
import com.dani.movies.databinding.ItemImageSliderBinding

class ImageSliderAdapter :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    private val dataList : MutableList<UpComingDto> = mutableListOf()

    fun addList(data : List<UpComingDto>){
        dataList.clear()
        dataList.addAll(data)
        dataList.removeDuplicatesItem()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ImageViewHolder(private val binding: ItemImageSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data : UpComingDto) {
            binding.run {
                imgPosterPath.loadImage(Network.IMG_URL+data.posterPath)
                imgBackdropPath.loadImage(Network.IMG_URL+data.backdropPath)
                txtTitle.text = data.originalTitle
                txtRelease.text = data.releaseDate
            }
        }
    }
}
