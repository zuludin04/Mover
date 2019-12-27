package com.app.zuludin.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.common.Event
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.*
import com.app.zuludin.data.utils.Resource
import com.app.zuludin.detail.adapter.DetailInfo
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {
    private var detailSource: LiveData<Resource<MovieDetailResponse>> = MutableLiveData()

    private val movieDetailData = MediatorLiveData<MovieDetailResponse>()
    val detail: LiveData<MovieDetailResponse> get() = movieDetailData

    private val detailDurationRelease = MutableLiveData<String>()
    val durationRelease: LiveData<String> = detailDurationRelease

    private val movieGenres = MutableLiveData<String>()
    val genres: LiveData<String> = movieGenres

    private val movieCountry = MutableLiveData<String>()
    val country: LiveData<String> = movieCountry

    private val movieDirector = MutableLiveData<String>()
    val director: LiveData<String> = movieDirector

    private val moreInfo = MutableLiveData<List<Any>>()
    val more: LiveData<List<Any>> = moreInfo

    private val moviePoster = MutableLiveData<String>()
    val poster: LiveData<String> = moviePoster

    fun loadDetailData(movieId: Int) = viewModelScope.launch(dispatchers.main) {
        movieDetailData.removeSource(detailSource)

        withContext(dispatchers.io) {
            detailSource = getMovieDetailUseCase(movieId.toString())
        }

        movieDetailData.addSource(detailSource) {
            movieDetailData.value = it.data

            it.data?.let { detail ->
                detailDurationRelease.value =
                    "${detail.runtime} min - ${detail.releaseDate}"
                movieGenres.value = detail.genres?.let { genre -> movieGenre(genre) }
                movieCountry.value = detail.productionCountries?.get(0)?.name
                movieDirector.value = detail.credits?.crew?.let { dir -> movieDirector(dir) }
                moreInfo.value = moreDetailInfo(
                    detail.productionCompanies,
                    detail.credits?.cast,
                    detail.similar?.results
                )
                moviePoster.value = it.data?.posterPath
            }

            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event(it.error?.message.toString())
        }
    }

    private fun moreDetailInfo(
        companies: List<ProductionCompaniesItem>?,
        cast: List<CastItem>?,
        similar: List<MovieResult>?
    ): List<Any> {
        return mutableListOf(
            "Production Company",
            DetailInfo(companies),
            "Movie Cast",
            DetailInfo(cast),
            "Similar Movies",
            DetailInfo(similar)
        )
    }

    private fun movieDirector(crews: List<CrewItem>): String {
        var director = ""

        for (i in crews.indices) {
            if (crews[i].job == "Director") {
                val crew = crews[i]
                director = crew.name.toString()
            }
        }

        return director
    }

    private fun movieGenre(genres: List<GenresItem>): String {
        var genre = ""
        for (i in genres.indices) {
            genre += "${genres[i].name}  "
        }
        return genre
    }
}