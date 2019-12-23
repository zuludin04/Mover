package com.app.zuludin.upcoming

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
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
class UpcomingViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getUpcomingMovieUseCase: GetUpcomingMovieUseCase
    private lateinit var viewModel: UpcomingViewModel
    private val dispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val movieList = listOf(
        MovieResult(id = 1, title = "Batman"),
        MovieResult(id = 2, title = "Superman"),
        MovieResult(id = 3, title = "The Flash")
    )

    @Before
    fun setupUpcomingMovieTest() {
        getUpcomingMovieUseCase = mockk()
    }

    @Test
    fun `Load upcoming movie when it is being called`() {
        val observer = mockk<Observer<Resource<List<MovieResult>>>>(relaxed = true)
        val result = Resource.success(movieList)

        coEvery { getUpcomingMovieUseCase() } returns MutableLiveData<Resource<List<MovieResult>>>().apply {
            value = result
        }

        viewModel = UpcomingViewModel(getUpcomingMovieUseCase, dispatchers)
        viewModel.movies.observeForever(observer)

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }
}