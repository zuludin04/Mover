package com.app.zuludin.popular.di

import com.app.zuludin.popular.GetPopularMovieUseCase
import com.app.zuludin.popular.PopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val popularModule = module {
    factory { GetPopularMovieUseCase(get()) }
    viewModel { PopularViewModel(get(), get()) }
}