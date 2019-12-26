package com.app.zuludin.nowplaying

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

class NowPlayingViewModel(
    private val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val nowPlayingMovies = MediatorLiveData<Resource<List<MovieResult>>>()
    val movies: LiveData<Resource<List<MovieResult>>> get() = nowPlayingMovies
    private var movieSource: LiveData<Resource<List<MovieResult>>> = MutableLiveData()

    private val errorResult = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = errorResult

    init {
        loadingNowPlayingMovie()
    }

    fun refreshLayout() = loadingNowPlayingMovie()

    private fun loadingNowPlayingMovie() = viewModelScope.launch(dispatchers.main) {
        nowPlayingMovies.removeSource(movieSource)
        withContext(dispatchers.io) {
            movieSource = getNowPlayingMovieUseCase()
        }

        nowPlayingMovies.addSource(movieSource) {
            nowPlayingMovies.value = it
            errorResult.value = it.status == Resource.Status.ERROR
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event("There is something wrong")
        }
    }
}