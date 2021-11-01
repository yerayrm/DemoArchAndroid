package com.yerayrm.dm.heroes.di

import com.yerayrm.dm.heroes.data.datasource.ApiDataSource
import com.yerayrm.dm.heroes.data.datasource.ApiService
import com.yerayrm.dm.heroes.data.repository.HeroRepository
import org.koin.dsl.module

val heroDataManagerProvider = module {
    single { ApiService.create() }
    single { ApiDataSource(get()) }
    single { HeroRepository(get()) }
}
