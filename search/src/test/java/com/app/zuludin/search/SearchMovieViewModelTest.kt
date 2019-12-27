package com.app.zuludin.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.utils.Resource
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class SearchMovieViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchMovieViewModel
    private lateinit var getSearchResultUseCase: GetSearchResultUseCase
    private val dispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val movieList = listOf(
        MovieResult(id = 1, title = "Batman"),
        MovieResult(id = 2, title = "Superman"),
        MovieResult(id = 3, title = "The Flash")
    )

    @Before
    fun setupSearchMovieViewModelTest() {
        getSearchResultUseCase = mockk()
    }

    @Test
    fun `search movie from TMDB api`() {
        val observer = mockk<Observer<Resource<List<MovieResult>>>>(relaxed = true)
        val result = Resource.success(movieList)

        coEvery { getSearchResultUseCase("batman") } returns MutableLiveData<Resource<List<MovieResult>>>().apply {
            value = result
        }

        viewModel = SearchMovieViewModel(getSearchResultUseCase, dispatchers)
        viewModel.result.observeForever(observer)
        viewModel.searchMovie("batman")

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }

    @Test
    fun `error when searching movie`() {
        val observer = mockk<Observer<Resource<List<MovieResult>>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<String>>>(relaxed = true)
        val result = Resource.error(Exception("Error"), null)

        coEvery { getSearchResultUseCase(any()) } returns MutableLiveData<Resource<List<MovieResult>>>().apply {
            value = result
        }

        viewModel = SearchMovieViewModel(getSearchResultUseCase, dispatchers)
        viewModel.result.observeForever(observer)
        viewModel.snackBarError.observeForever(observerSnackBar)
        viewModel.searchMovie("btm")

        verify {
            observer.onChanged(result)
            observerSnackBar.onChanged(viewModel.snackBarError.value)
        }

        confirmVerified(observer)
    }
}