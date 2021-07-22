package com.dani.movies.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dani.data.loaderDialog
import com.dani.data.toJson
import com.dani.movies.R
import com.dani.movies.databinding.FragmentMovieBinding
import com.dani.movies.ui.adapter.MovieAdapter
import com.dani.movies.utils.Mapper
import com.dani.movies.viewmodel.MoviesViewModel

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val viewModel : MoviesViewModel by activityViewModels()
    private val binding : FragmentMovieBinding by viewBinding()
    private val movieAdapter by lazy {
        MovieAdapter()
    }
    private val loader by lazy {
        context?.loaderDialog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            rvMovie.layoutManager = GridLayoutManager(context,1)
            rvMovie.adapter = movieAdapter
            movieAdapter.onClick {
                val movieId = it.id
                viewModel.navigateToDetailMovie(movieId)
            }
        }
        viewModel.getLocaleMovies()
        initObserver()
    }

    private fun initObserver(){
        loader?.show()
        viewModel.movies.observe(viewLifecycleOwner,{ data ->
            loader?.dismiss()
            Log.d("Movie Data ", "initObserver: remote data is -> ${data.toJson()}")
            if (data != null){
                movieAdapter.addList(data)
            }else{
                Toast.makeText(context,"Somthing went wrong",Toast.LENGTH_LONG).show()
            }
        })

        viewModel.localeMovies.observe(viewLifecycleOwner,{ localeData ->
            Log.d("Locale Data", "initObserver: localeData is -> ${localeData.toJson()}")
            loader?.dismiss()
            if (localeData.isNotEmpty()){
                val mapToMovieDto = Mapper.mapMovieEntityToDto(localeData)
                movieAdapter.addList(mapToMovieDto)
            }else{
                viewModel.getMovies()
            }
        })
    }

}