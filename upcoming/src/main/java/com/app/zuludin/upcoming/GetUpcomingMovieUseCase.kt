package com.app.zuludin.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.app.zuludin.data.Repository
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.utils.Resource

class GetUpcomingMovieUseCase(private val repository: Repository) {
    suspend operator fun invoke(): LiveData<Resource<List<MovieResult>>> {
        return Transformations.map(repository.loadUpcomingMovie()) { it }
    }
}