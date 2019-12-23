package com.app.zuludin.popular

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

class PopularViewModel(
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val _popularMovies = MediatorLiveData<Resource<List<MovieResult>>>()
    val populars: LiveData<Resource<List<MovieResult>>> get() = _popularMovies
    private var movieSource: LiveData<Resource<List<MovieResult>>> = MutableLiveData()

    init {
        loadPopularMovie()
    }

    private fun loadPopularMovie() = viewModelScope.launch(dispatchers.main) {
        showLoading(true)
        _popularMovies.removeSource(movieSource)
        withContext(dispatchers.io) {
            movieSource = getPopularMovieUseCase()
        }
        _popularMovies.addSource(movieSource) {
            _popularMovies.value = it
            showLoading(false)
            if (it.status == Resource.Status.ERROR) {
                _snackBarError.value = Event("There something wrong")
                showLoading(false)
            }
        }
    }

}