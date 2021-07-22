package com.dani.favorites.ui.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dani.data.loaderDialog
import com.dani.favorites.R
import com.dani.favorites.databinding.FragmentFavoriteBinding
import com.dani.favorites.ui.adapter.FavoriteAdapter
import com.dani.favorites.viewmodel.FavoriteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewModel: FavoriteViewModel by activityViewModels()
    private val binding: FragmentFavoriteBinding by viewBinding()
    private val favoriteAdapter by lazy {
        FavoriteAdapter()
    }
    private val loader by lazy {
        context?.loaderDialog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            rvFavorite.layoutManager = GridLayoutManager(context, 1)
            rvFavorite.adapter = favoriteAdapter
            favoriteAdapter.onClickFavorite {
                val movieId = it.movieId
                viewModel.navigateToDetailMovie(movieId)
            }
            initObserver()

        }
    }

    private fun initObserver() {
        loader?.show()
        viewModel.getFavoriteMovies()
        viewModel.getFavoriteMovie.observe(viewLifecycleOwner, { data ->
            lifecycleScope.launch {
                delay(1000)
                favoriteAdapter.addList(data)
                binding.txtEmpty.isVisible = data.isEmpty()
                loader?.dismiss()
            }
        })
    }
}