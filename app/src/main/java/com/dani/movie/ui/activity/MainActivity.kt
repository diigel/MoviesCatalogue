package com.dani.movie.ui.activity

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dani.details.viewmodel.DetailMovieViewModel
import com.dani.favorites.viewmodel.FavoriteViewModel
import com.dani.movie.R
import com.dani.movie.databinding.ActivityMainBinding
import com.dani.movie.utils.Mapper
import com.dani.movie.viewmodel.SearchViewModel
import com.dani.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by viewBinding()

    private val movieViewModel : MoviesViewModel by viewModel()
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val detailViewModel: DetailMovieViewModel by viewModel()
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            val navFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNav,navFragment.navController)

            movieViewModel.navigateToDetailMovie.observe(this@MainActivity) {
                navigateToDetailMovie(navFragment, it)
            }

            favoriteViewModel.navigateToDetailMovie.observe(this@MainActivity) {
                navigateToDetailMovie(navFragment, it)
            }

            navFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
                val currentIdRes = destination.id
                bottomNav.isVisible = currentIdRes == R.id.movieFragment || currentIdRes == R.id.favoriteFragment
                txtSearch.isVisible = currentIdRes == R.id.movieFragment
            }

            detailViewModel.requestCheckFavorite.observe(this@MainActivity) { id ->
                lifecycleScope.launch {
                    favoriteViewModel.requestCheckFavoriteById(id)
                        .observe(this@MainActivity) { hasFavorite ->
                            detailViewModel.setHasFavorite(hasFavorite)
                        }
                }
            }

            detailViewModel.requestInsertFavorite.observe(this@MainActivity) { movieDto ->
                favoriteViewModel.addFavoriteMovie(Mapper.mapDetailMovieDtoToFavoriteEntity(movieDto))
            }

            detailViewModel.requestRemoveFavorite.observe(this@MainActivity) { id ->
                favoriteViewModel.removeFavoriteMovie(id)
                navFragment.navController.popBackStack(R.id.favoriteFragment, true)
            }

            cardSearch.setOnClickListener {
                navFragment.navController.navigate(R.id.searchFragment)
            }

            searchViewModel.navigateToDetailMovie.observe(this@MainActivity) {
                navigateToDetailMovie(navFragment, it)
            }

        }
    }

    private fun navigateToDetailMovie(navFragment: NavHostFragment,movieId : Int){
        val bundle = bundleOf(
            "movie_id" to movieId
        )
        navFragment.navController.navigate(R.id.action_to_detailMovieFragment, bundle)
    }
}