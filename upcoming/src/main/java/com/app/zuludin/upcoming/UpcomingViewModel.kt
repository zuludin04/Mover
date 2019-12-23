package com.app.zuludin.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.common.Event
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpcomingViewModel(
    private val getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val upcomingMovies = MediatorLiveData<Resource<List<MovieResult>>>()
    val movies: LiveData<Resource<List<MovieResult>>> get() = upcomingMovies
    private var movieSource: LiveData<Resource<List<MovieResult>>> = MutableLiveData()

    init {
        loadUpcomingMovie()
    }

    private fun loadUpcomingMovie() = viewModelScope.launch(dispatchers.main) {
        upcomingMovies.removeSource(movieSource)
        withContext(dispatchers.io) {
            movieSource = getUpcomingMovieUseCase()
        }

        upcomingMovies.addSource(movieSource) {
            upcomingMovies.value = it
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event("There is something error")
        }
    }
}