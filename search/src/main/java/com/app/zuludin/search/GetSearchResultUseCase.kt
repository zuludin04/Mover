package com.app.zuludin.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.app.zuludin.data.Repository
import com.app.zuludin.data.model.MovieResult
import com.app.zuludin.data.utils.Resource

class GetSearchResultUseCase(private val repository: Repository) {
    suspend operator fun invoke(query: String): LiveData<Resource<List<MovieResult>>> {
        return Transformations.map(repository.loadSearchResult(query)) { it }
    }
}