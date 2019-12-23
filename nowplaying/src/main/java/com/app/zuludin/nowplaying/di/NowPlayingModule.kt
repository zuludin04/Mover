package com.app.zuludin.nowplaying.di

import com.app.zuludin.nowplaying.GetNowPlayingMovieUseCase
import com.app.zuludin.nowplaying.NowPlayingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val nowPlayingModule = module {
    factory { GetNowPlayingMovieUseCase(get()) }
    viewModel { NowPlayingViewModel(get(), get()) }
}