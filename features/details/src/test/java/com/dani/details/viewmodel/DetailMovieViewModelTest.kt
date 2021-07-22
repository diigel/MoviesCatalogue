package com.dani.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dani.details.data.Services
import com.dani.details.repository.DetailMovieRepository
import com.dani.details.repository.DetailMovieRepositoryImp
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

class DetailMovieViewModelTest {

    @get : Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get : Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var repository: DetailMovieRepository
    private lateinit var services: Services

    private val mockWebServer = MockWebServer()

    @Before
    fun `setup before`() {
        services = ServiceMock.create(mockWebServer.url("/"))
        repository = DetailMovieRepositoryImp(services)
        viewModel = DetailMovieViewModel(repository)
    }

    @After
    fun `setup after`() {
        mockWebServer.shutdown()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `detail movies should be success`() = runBlockingTest {
        mockWebServer.mockResponse("detail-movie-success-response.json", 200)

        viewModel.getDetailMovie(851438)
        val result = viewModel.detailMovie.getOrAwaitValue()

        val expectationIds = 851438
        val actual = result.id

        println("-------------- result detail movie")
        println(result)
        println("-------------- end result detail movie")
        assertEquals(expectationIds, actual)
    }
}