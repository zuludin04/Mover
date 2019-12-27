package com.app.zuludin.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.MovieDetailResponse
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
class MovieDetailViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase
    private lateinit var viewModel: MovieDetailViewModel
    private val dispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val detailMovie = MovieDetailResponse(
        id = 1, title = "Batman"
    )

    @Before
    fun setupMovieDetailTest() {
        getMovieDetailUseCase = mockk()
        viewModel = MovieDetailViewModel(getMovieDetailUseCase, dispatchers)
    }

    @Test
    fun `load movie detail by ID when open the page`() {
        val observer = mockk<Observer<MovieDetailResponse>>(relaxed = true)
        val result = Resource.success(detailMovie)

        coEvery { getMovieDetailUseCase("1") } returns MutableLiveData<Resource<MovieDetailResponse>>().apply {
            value = result
        }

        viewModel.detail.observeForever(observer)
        viewModel.loadDetailData(1)

        verify {
            observer.onChanged(result.data)
        }

        confirmVerified(observer)
    }

    @Test
    fun `load detail movie but showing error message`() {
        val observer = mockk<Observer<MovieDetailResponse>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<String>>>(relaxed = true)
        val result = Resource.error(Exception("Error"), null)

        coEvery { getMovieDetailUseCase(any()) } returns MutableLiveData<Resource<MovieDetailResponse>>().apply {
            value = result
        }

        viewModel.detail.observeForever(observer)
        viewModel.snackBarError.observeForever(observerSnackBar)
        viewModel.loadDetailData(1)

        verify {
            observer.onChanged(result.data)
            observerSnackBar.onChanged(viewModel.snackBarError.value)
        }

        confirmVerified(observer)
    }
}