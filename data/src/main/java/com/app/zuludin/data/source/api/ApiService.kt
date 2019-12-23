package com.app.zuludin.data.source.api

import com.app.zuludin.data.model.MovieDetailResponse
import com.app.zuludin.data.model.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun fetchMovieNowPlayingAsync(@Query("api_key") key: String): Deferred<MovieResponse>

    @GET("movie/upcoming")
    fun fetchUpcomingMovieAsync(@Query("api_key") key: String): Deferred<MovieResponse>

    @GET("movie/popular")
    fun fetchPopularMovieAsync(@Query("api_key") key: String): Deferred<MovieResponse>

    @GET("movie/{id}")
    fun fetchMovieDetailAsync(
        @Path("id") movieId: Int,
        @Query("api_key") api_key: String,
        @Query("append_to_response") append_to_response: String
    ): Deferred<MovieDetailResponse>

    @GET("search/movie")
    fun fetchSearchMovieResultAsync(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Deferred<MovieResponse>
}