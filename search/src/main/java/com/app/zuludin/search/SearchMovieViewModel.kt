package com.app.zuludin.search

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

class SearchMovieViewModel(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val movieSearchResult = MediatorLiveData<Resource<List<MovieResult>>>()
    val result: LiveData<Resource<List<MovieResult>>> get() = movieSearchResult
    private var searchSource: LiveData<Resource<List<MovieResult>>> = MutableLiveData()

    fun searchMovie(query: String) = viewModelScope.launch(dispatchers.main) {
        movieSearchResult.removeSource(searchSource)

        withContext(dispatchers.io) {
            searchSource = getSearchResultUseCase(query)
        }

        movieSearchResult.addSource(searchSource) {
            movieSearchResult.value = it
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event("There is something wrong")
        }
    }
}