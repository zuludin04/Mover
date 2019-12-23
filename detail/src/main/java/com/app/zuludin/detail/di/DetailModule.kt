package com.app.zuludin.detail.di

import com.app.zuludin.detail.GetMovieDetailUseCase
import com.app.zuludin.detail.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { GetMovieDetailUseCase(get()) }
    viewModel { MovieDetailViewModel(get(), get()) }
}