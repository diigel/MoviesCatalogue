package com.dani.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dani.data.toJson
import com.dani.movies.Constant
import com.dani.movies.MoviesFakeDao
import com.dani.movies.dao.MovieDao
import com.dani.movies.data.Services
import com.dani.movies.repository.MoviesRepository
import com.dani.movies.repository.MoviesRepositoryImpl
import com.dani.testutils.MainCoroutineRule
import com.dani.testutils.ServiceMock
import com.dani.testutils.getOrAwaitValue
import com.dani.testutils.mockResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @get : Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get : Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MoviesViewModel
    private lateinit var repository: MoviesRepository
    private lateinit var services: Services
    private lateinit var moviesDao : MovieDao

    private val mockWebServer = MockWebServer()

    @Before
    fun `setup before`() {
        moviesDao = MoviesFakeDao()
        services = ServiceMock.create(mockWebServer.url("/"))
        repository = MoviesRepositoryImpl(services,moviesDao)
        viewModel = MoviesViewModel(repository)
    }

    @After
    fun `setup after`() {
        mockWebServer.shutdown()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `movies should be success`() = runBlockingTest {
        mockWebServer.mockResponse("movie-success-response.json", 200)

        viewModel.getMovies()
        val result = viewModel.movies.getOrAwaitValue()

        // create hardcore list id from  response
        val expectationIds = listOf(497698, 379686, 602223, 459151, 588228)

        // get list id from response
        val actual = result.map { it.id }

        // hardcore id and list id response must be the same
        println("result ----")
        println(result.toJson())
        println("result end ----")
        println()

        assertEquals(expectationIds, actual)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `locale movies should be success` () = runBlockingTest {
        viewModel.addLocaleMovies(Constant.movie1)

        viewModel.getLocaleMovies()

        val getLocaleMovie = viewModel.localeMovies.getOrAwaitValue()

        // create hardcore list id from  response
        val expectationListIds = listOf(Constant.movie1)

        // hardcore id and list id response must be the same
        println("result ----")
        println(getLocaleMovie.toJson())
        println("result end ----")
        println()

        assertEquals(expectationListIds, getLocaleMovie)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `remove locale movies should be success` () = runBlockingTest {
        viewModel.addLocaleMovies(Constant.movie1)
        viewModel.addLocaleMovies(Constant.movie2)

        viewModel.removeLocaleMovies(Constant.movie3)

        viewModel.getLocaleMovies()
        val getLocaleMovie = viewModel.localeMovies.getOrAwaitValue()

        val expectationEntity = Constant.movie1
        val resultEntity = getLocaleMovie.find { it.movieId == expectationEntity.movieId }

        assertEquals(expectationEntity,resultEntity)
    }


}