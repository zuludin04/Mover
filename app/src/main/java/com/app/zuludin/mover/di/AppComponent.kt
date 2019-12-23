package com.app.zuludin.mover.di

import com.app.zuludin.data.di.dataSourceModule
import com.app.zuludin.data.di.repositoryModule
import com.app.zuludin.detail.di.detailModule
import com.app.zuludin.nowplaying.di.nowPlayingModule
import com.app.zuludin.popular.di.popularModule
import com.app.zuludin.search.di.searchModule
import com.app.zuludin.upcoming.di.upcomingModule

val appComponent = listOf(
    dataSourceModule,
    repositoryModule,
    popularModule,
    nowPlayingModule,
    upcomingModule,
    detailModule,
    searchModule
)