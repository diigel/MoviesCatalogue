package com.dani.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dani.data.toJson
import com.dani.movie.data.Services
import com.dani.movie.repository.SearchRepository
import com.dani.movie.repository.SearchRepositoryImpl
import com.dani.testutils.MainCoroutineRule
import com.dani.testutils.ServiceMock
import com.dani.testutils.getOrAwaitValue
import com.dani.testutils.mockResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {
    @get: Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get : Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchViewModel
    private lateinit var repository: SearchRepository
    private lateinit var services: Services

    private val mockWebServer = MockWebServer()


    @Before
    fun `setup before`() {
        services = ServiceMock.create(mockWebServer.url("/"))
        repository = SearchRepositoryImpl(services)
        viewModel = SearchViewModel(repository)
    }

    @After
    fun `setup after`() {
        mockWebServer.shutdown()
    }

    @Test
    fun `search movie should be success`() = runBlocking {
        mockWebServer.mockResponse("search-success-response.json", 200)

        viewModel.requestSearch("aaa")
        val result = viewModel.requestSearch.getOrAwaitValue()

        val expectationIds = listOf(69738, 476199, 546205)
        val actual = result.map { it.id }

        println("-------- result search ")
        println(result.toJson())
        println("-------- end result search ")

        assertEquals(expectationIds, actual)
    }
}