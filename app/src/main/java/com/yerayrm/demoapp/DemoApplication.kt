package com.yerayrm.demoapp

import android.app.Application
import com.yerayrm.dm.heroes.di.heroDataManagerProvider
import com.yerayrm.feature.herolist.di.heroListProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupInjection()
    }

    private fun setupInjection() {
        startKoin {
            androidContext(this@DemoApplication)
            modules(listOf(heroListProvider, heroDataManagerProvider))
        }
    }
}