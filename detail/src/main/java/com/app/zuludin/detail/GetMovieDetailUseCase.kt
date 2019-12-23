package com.app.zuludin.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.app.zuludin.data.Repository
import com.app.zuludin.data.model.MovieDetailResponse
import com.app.zuludin.data.utils.Resource

class GetMovieDetailUseCase(private val repository: Repository) {
    suspend operator fun invoke(movieId: String): LiveData<Resource<MovieDetailResponse>> {
        return Transformations.map(repository.loadMovieDetail(movieId.toInt())) {it}
    }
}