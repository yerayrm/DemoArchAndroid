package com.yerayrm.feature.herolist.di

import com.yerayrm.feature.herolist.viewmodels.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val heroListProvider = module {
    viewModel { HeroListViewModel(get()) }
}