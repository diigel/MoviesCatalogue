package com.dani.movies.ui.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dani.data.loaderDialog
import com.dani.movies.R
import com.dani.movies.databinding.FragmentMovieBinding
import com.dani.movies.ui.adapter.ImageSliderAdapter
import com.dani.movies.ui.adapter.MovieAdapter
import com.dani.movies.utils.Mapper
import com.dani.movies.viewmodel.MoviesViewModel

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val viewModel : MoviesViewModel by activityViewModels()
    private val binding : FragmentMovieBinding by viewBinding()
    private val movieAdapter by lazy {
        MovieAdapter()
    }

    private val imageSliderAdapter by lazy {
        ImageSliderAdapter()
    }
    private val loader by lazy {
        context?.loaderDialog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            viewPager.adapter = imageSliderAdapter
            dotsIndicator.setViewPager2(viewPager)
            rvMovie.layoutManager = GridLayoutManager(context,1)
            rvMovie.adapter = movieAdapter
            movieAdapter.onClick {
                val movieId = it.id
                viewModel.navigateToDetailMovie(movieId)
            }
        }
        viewModel.getLocaleMovies()
        viewModel.getUpComing()
        initObserver()
    }

    private fun initObserver(){
        loader?.show()
        viewModel.movies.observe(viewLifecycleOwner) { data ->
            loader?.dismiss()
            if (data != null) {
                movieAdapter.addList(data)
            } else {
                Toast.makeText(context, getString(R.string.str_general_error), Toast.LENGTH_LONG).show()
            }
        }

        viewModel.localeMovies.observe(viewLifecycleOwner) { localeData ->
            loader?.dismiss()
            if (localeData.isNotEmpty()) {
                val mapToMovieDto = Mapper.mapMovieEntityToDto(localeData)
                movieAdapter.addList(mapToMovieDto)
            } else {
                viewModel.getMovies()
            }
        }

        viewModel.upComing.observe(viewLifecycleOwner) { dataUpComing ->
            loader?.dismiss()
            if (dataUpComing != null){
                imageSliderAdapter.addList(dataUpComing.slice(0..5))
            }else {
                Toast.makeText(context, getString(R.string.str_general_error), Toast.LENGTH_LONG).show()
            }
        }
    }

}