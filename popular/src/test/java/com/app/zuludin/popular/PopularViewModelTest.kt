package com.app.zuludin.popular

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
class PopularViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getPopularMovieUseCase: GetPopularMovieUseCase
    private lateinit var viewModel: PopularViewModel
    private val appDispatcher = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val movieList = listOf(
        MovieResult(id = 1, title = "Batman"),
        MovieResult(id = 2, title = "Superman"),
        MovieResult(id = 3, title = "The Flash")
    )

    @Before
    fun setupTest() {
        getPopularMovieUseCase = mockk()
    }

    @Test
    fun `Load data popular movie when ViewModel is created`() {
        val observer = mockk<Observer<Resource<List<MovieResult>>>>(relaxed = true)
        val result = Resource.success(movieList)

        coEvery { getPopularMovieUseCase() } returns MutableLiveData<Resource<List<MovieResult>>>().apply {
            value = result
        }

        viewModel = PopularViewModel(getPopularMovieUseCase, appDispatcher)
        viewModel.populars.observeForever(observer)

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }
}