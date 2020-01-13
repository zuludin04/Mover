package com.app.zuludin.data.source.api

import com.app.zuludin.data.model.MovieDetailResponse
import com.app.zuludin.data.model.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{type}")
    fun fetchMovieListAsync(
        @Path("type") path: String,
        @Query("api_key") key: String
    ): Deferred<MovieResponse>

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