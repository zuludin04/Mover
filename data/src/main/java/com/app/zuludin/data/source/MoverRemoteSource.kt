package com.app.zuludin.data.source

import com.app.zuludin.data.BuildConfig
import com.app.zuludin.data.source.api.ApiService

class MoverRemoteSource(private val service: ApiService) {

    fun fetchPopularMovieAsync() =
        service.fetchMovieListAsync("popular", BuildConfig.TMDB_API_KEY)

    fun fetchUpcomingMovieAsync() =
        service.fetchMovieListAsync("upcoming", BuildConfig.TMDB_API_KEY)

    fun fetchNowPlayingMovieAsync() =
        service.fetchMovieListAsync("now_playing", BuildConfig.TMDB_API_KEY)

    fun fetchMovieDetailAsync(movieId: Int) =
        service.fetchMovieDetailAsync(
            movieId,
            BuildConfig.TMDB_API_KEY,
            "credits,similar,videos"
        )

    fun fetchSearchResultAsync(query: String) =
        service.fetchSearchMovieResultAsync(BuildConfig.TMDB_API_KEY, query)
}