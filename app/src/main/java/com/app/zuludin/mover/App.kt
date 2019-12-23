package com.app.zuludin.mover

import android.app.Application
import com.app.zuludin.mover.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    open fun configureDi() = startKoin {
        androidContext(this@App)
        modules(appComponent)
    }
}