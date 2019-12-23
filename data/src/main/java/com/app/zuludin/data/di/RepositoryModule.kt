package com.app.zuludin.data.di

import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.MoverRepository
import com.app.zuludin.data.Repository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory { MoverRepository(get()) as Repository }
}