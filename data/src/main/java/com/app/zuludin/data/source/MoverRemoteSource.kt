package com.app.zuludin.data.source

import com.app.zuludin.data.source.api.ApiService

class MoverRemoteSource(private val service: ApiService) {

    fun fetchPopularMovieAsync() =
        service.fetchPopularMovieAsync("325705eafaaf6af0062a23113f72a058")

    fun fetchUpcomingMovieAsync() =
        service.fetchUpcomingMovieAsync("325705eafaaf6af0062a23113f72a058")

    fun fetchNowPlayingMovieAsync() =
        service.fetchMovieNowPlayingAsync("325705eafaaf6af0062a23113f72a058")

    fun fetchMovieDetailAsync(movieId: Int) =
        service.fetchMovieDetailAsync(
            movieId,
            "325705eafaaf6af0062a23113f72a058",
            "credits,similar,videos"
        )

    fun fetchSearchResultAsync(query: String) =
        service.fetchSearchMovieResultAsync("325705eafaaf6af0062a23113f72a058", query)
}