package com.dani.details.ui

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dani.data.Network
import com.dani.data.loadImage
import com.dani.data.loaderDialog
import com.dani.details.R
import com.dani.details.data.entity.DetailMovieDto
import com.dani.details.databinding.FragmentDetailMovieBinding
import com.dani.details.viewmodel.DetailMovieViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private val viewModel : DetailMovieViewModel by activityViewModels()
    private val binding : FragmentDetailMovieBinding by viewBinding()

    private val loader by lazy {
        context?.loaderDialog()
    }

    private val movieId by lazy {
        arguments?.getInt("movie_id")
    }

    private val drawableFavorite by lazy {
        ContextCompat.getDrawable(requireContext(), R.drawable.ic_star)
    }

    private val drawableUnFavorite by lazy {
        ContextCompat.getDrawable(requireContext(), R.drawable.ic_star_border)
    }

    private var hasFavorite = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        viewModel.getDetailMovie(movieId ?: 0)
    }

    private fun initObserver(){
        loader?.show()
        viewModel.detailMovie.observe(viewLifecycleOwner,{ data ->
            lifecycleScope.launch {
                delay(2000)
                loader?.dismiss()
                if (data != null){
                    initView(data)
                }else{
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initView(data : DetailMovieDto) = binding.run {
        txtTitle.text = data.originalTitle
        txtReleaseDate.text = data.releaseDate
        txtRating.text = data.popularity.toString()
        txtOverview.text = data.overview
        imgMoviePoster.loadImage(Network.IMG_URL+data.posterPath)
        imgMovieBackdrop.loadImage(Network.IMG_URL+data.backdropPath)
        viewModel.checkHasFavorite(movieId)
        viewModel.hasFavorite.observe(viewLifecycleOwner, { favorite ->
            hasFavorite = favorite
            val drawable = if (favorite) {
                drawableFavorite
            } else {
                drawableUnFavorite
            }
            imgAddFavorite.setImageDrawable(drawable)
        })

        imgAddFavorite.setOnClickListener {
            if (hasFavorite) {
                viewModel.removeFavorite(data.id)
                Toast.makeText(context, "Remove Favorite", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertFavorite(data)
                Toast.makeText(context, "Adding Favorite", Toast.LENGTH_SHORT).show()
            }

            lifecycleScope.launch {
                delay(200)
                viewModel.checkHasFavorite(movieId)
            }
        }
    }
}