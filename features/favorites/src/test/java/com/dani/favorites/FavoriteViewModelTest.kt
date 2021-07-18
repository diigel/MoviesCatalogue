package com.dani.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dani.data.toJson
import com.dani.favorites.dao.FavoriteDao
import com.dani.favorites.repository.FavoriteRepository
import com.dani.favorites.repository.FavoriteRepositoryImpl
import com.dani.favorites.viewmodel.FavoriteViewModel
import com.dani.testutils.MainCoroutineRule
import com.dani.testutils.getOrAwaitValue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteViewModelTest {

    @get : Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get : Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var repository: FavoriteRepository
    private lateinit var dao: FavoriteDao

    @Before
    fun `setup before`() {
        dao = FavoriteFakeDao()
        repository = FavoriteRepositoryImpl(dao)
        viewModel = FavoriteViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `add favorite movie should be success`() = runBlockingTest {
        viewModel.addFavoriteMovie(Constant.movie1)
        viewModel.addFavoriteMovie(Constant.movie2)
        viewModel.addFavoriteMovie(Constant.movie3)
        viewModel.addFavoriteMovie(Constant.movie4)

        viewModel.getFavoriteMovies()
        val result = viewModel.getFavoriteMovie.getOrAwaitValue()

        val expectationEntity = Constant.listMovieEntity

        println("------------- result add favorite")
        println(result)
        println("------------- end result add favorite")

        assertEquals(expectationEntity, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `remove favorite movie should be success`() = runBlockingTest {

        // add favorite movie
        viewModel.addFavoriteMovie(Constant.movie1)
        viewModel.addFavoriteMovie(Constant.movie2)

        // remove favorite movie
        viewModel.removeFavoriteMovie(Constant.movie1)

        // get all favorite movie
        viewModel.getFavoriteMovies()
        val result = viewModel.getFavoriteMovie.getOrAwaitValue()

        val expectationEntity = Constant.movie2
        val resultEntity = result.find { it.movieId == expectationEntity.movieId }

        // show data list for check entity movie 2 not remove
        println("------------- result remove favorite")
        println(result.toJson())
        println("------------- end result remove favorite")

        // show data remove for check entity movie 1 is remove
        println("------------- remove entity favorite ")
        val removeEntity = Constant.movie1
        println(result.find { it.movieId == removeEntity.movieId }?.toJson())
        println("------------- end remove entity favorite")

        assertEquals(expectationEntity, resultEntity)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get by id favorite movie should be success`() = runBlockingTest {

        // add favorite
        viewModel.addFavoriteMovie(Constant.movie1)
        viewModel.addFavoriteMovie(Constant.movie2)
        viewModel.addFavoriteMovie(Constant.movie3)
        viewModel.addFavoriteMovie(Constant.movie4)

        //get favorite by movie id
        viewModel.getFavoriteMovie(Constant.movie1.movieId)
        val result = viewModel.getFavoriteMovieById.getOrAwaitValue()

        val expectationEntity = Constant.movie1

        println("---------------- result")
        println(result.toJson())
        println("---------------- end result")

        assertEquals(expectationEntity, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get favorite movie should be success`() = runBlockingTest {

        viewModel.addFavoriteMovie(Constant.movie1)
        viewModel.addFavoriteMovie(Constant.movie2)
        viewModel.addFavoriteMovie(Constant.movie3)
        viewModel.addFavoriteMovie(Constant.movie4)

        viewModel.getFavoriteMovies()
        val result = viewModel.getFavoriteMovie.getOrAwaitValue()

        val expectationEntity = Constant.movie3
        val resultEntity = result.find { it.movieId == expectationEntity.movieId }

        println(result)

        assertEquals(expectationEntity, resultEntity)
    }

}