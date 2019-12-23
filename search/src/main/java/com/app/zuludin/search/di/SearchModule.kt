package com.app.zuludin.search.di

import com.app.zuludin.search.GetSearchResultUseCase
import com.app.zuludin.search.SearchMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory { GetSearchResultUseCase(get()) }
    viewModel { SearchMovieViewModel(get(), get()) }
}