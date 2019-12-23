package com.app.zuludin.data

import androidx.lifecycle.LiveData
import com.app.zuludin.data.model.MovieDetailResponse
import com.app.zuludin.data.model.MovieResponse
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.source.MoverRemoteSource
import com.app.zuludin.data.utils.NetworkBoundResource
import com.app.zuludin.data.utils.Resource
import kotlinx.coroutines.Deferred

interface Repository {
    suspend fun loadPopularMovie(): LiveData<Resource<List<MovieResult>>>
    suspend fun loadNowPlayingMovie(): LiveData<Resource<List<MovieResult>>>
    suspend fun loadUpcomingMovie(): LiveData<Resource<List<MovieResult>>>
    suspend fun loadMovieDetail(movieId: Int): LiveData<Resource<MovieDetailResponse>>
    suspend fun loadSearchResult(query: String): LiveData<Resource<List<MovieResult>>>
}

class MoverRepository(
    private val remote: MoverRemoteSource
) : Repository {
    override suspend fun loadPopularMovie(): LiveData<Resource<List<MovieResult>>> {
        return object : NetworkBoundResource<List<MovieResult>, MovieResponse>() {
            override fun processResponse(response: MovieResponse?): List<MovieResult>? =
                response?.results

            override fun shouldFetch(data: List<MovieResult>?): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<MovieResponse> =
                remote.fetchPopularMovieAsync()
        }.build().asLiveData()
    }

    override suspend fun loadNowPlayingMovie(): LiveData<Resource<List<MovieResult>>> {
        return object : NetworkBoundResource<List<MovieResult>, MovieResponse>() {
            override fun processResponse(response: MovieResponse?): List<MovieResult>? =
                response?.results

            override fun shouldFetch(data: List<MovieResult>?): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<MovieResponse> =
                remote.fetchNowPlayingMovieAsync()
        }.build().asLiveData()
    }

    override suspend fun loadUpcomingMovie(): LiveData<Resource<List<MovieResult>>> {
        return object : NetworkBoundResource<List<MovieResult>, MovieResponse>() {
            override fun processResponse(response: MovieResponse?): List<MovieResult>? =
                response?.results

            override fun shouldFetch(data: List<MovieResult>?): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<MovieResponse> =
                remote.fetchUpcomingMovieAsync()
        }.build().asLiveData()
    }

    override suspend fun loadMovieDetail(movieId: Int): LiveData<Resource<MovieDetailResponse>> {
        return object : NetworkBoundResource<MovieDetailResponse, MovieDetailResponse>() {
            override fun processResponse(response: MovieDetailResponse?): MovieDetailResponse? =
                response

            override fun shouldFetch(data: MovieDetailResponse?): Boolean = data == null

            override fun createCallAsync(): Deferred<MovieDetailResponse> =
                remote.fetchMovieDetailAsync(movieId)
        }.build().asLiveData()
    }

    override suspend fun loadSearchResult(query: String): LiveData<Resource<List<MovieResult>>> {
        return object : NetworkBoundResource<List<MovieResult>, MovieResponse>() {
            override fun processResponse(response: MovieResponse?): List<MovieResult>? =
                response?.results

            override fun shouldFetch(data: List<MovieResult>?): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<MovieResponse> =
                remote.fetchSearchResultAsync(query)
        }.build().asLiveData()
    }
}