package com.app.zuludin.nowplaying

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
class NowPlayingViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase
    private lateinit var viewModel: NowPlayingViewModel
    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val movieList = listOf(
        MovieResult(id = 1, title = "Batman"),
        MovieResult(id = 2, title = "Superman"),
        MovieResult(id = 3, title = "The Flash")
    )

    @Before
    fun setupNowPlayingTest() {
        getNowPlayingMovieUseCase = mockk()
    }

    @Test
    fun `Load now playing movie when ViewModel is loaded`() {
        val observer = mockk<Observer<Resource<List<MovieResult>>>>(relaxed = true)
        val result = Resource.success(movieList)

        coEvery { getNowPlayingMovieUseCase() } returns MutableLiveData<Resource<List<MovieResult>>>().apply {
            value = result
        }

        viewModel = NowPlayingViewModel(getNowPlayingMovieUseCase, appDispatchers)
        viewModel.movies.observeForever(observer)

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }

    @Test
    fun `showing error result when trying to load data`() {
        val observer = mockk<Observer<Resource<List<MovieResult>>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<String>>>(relaxed = true)
        val result = Resource.error(Exception("Error"), null)

        coEvery { getNowPlayingMovieUseCase() } returns MutableLiveData<Resource<List<MovieResult>>>().apply {
            value = result
        }

        viewModel = NowPlayingViewModel(getNowPlayingMovieUseCase, appDispatchers)
        viewModel.movies.observeForever(observer)
        viewModel.snackBarError.observeForever(observerSnackBar)

        verify {
            observer.onChanged(result)
            observerSnackBar.onChanged(viewModel.snackBarError.value)
        }

        confirmVerified(observer)
    }
}