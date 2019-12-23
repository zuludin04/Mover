package com.app.zuludin.upcoming.di

import com.app.zuludin.upcoming.GetUpcomingMovieUseCase
import com.app.zuludin.upcoming.UpcomingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val upcomingModule = module {
    factory { GetUpcomingMovieUseCase(get()) }
    viewModel { UpcomingViewModel(get(), get()) }
}