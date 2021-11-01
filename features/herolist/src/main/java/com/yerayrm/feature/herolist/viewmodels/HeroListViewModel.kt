package com.yerayrm.feature.herolist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yerayrm.dm.heroes.data.repository.HeroRepository
import com.yerayrm.dm.heroes.model.Hero
import com.yerayrm.dm.heroes.utils.DataResult
import kotlinx.coroutines.launch

class HeroListViewModel(
    private val heroRepository: HeroRepository
) : ViewModel() {

    val heroes: MutableLiveData<List<Hero>> = MutableLiveData(emptyList())
    val showError: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getHeroes() {
        viewModelScope.launch {
            when (val result = heroRepository.getHeroes()) {
                is DataResult.Success -> {
                    if (result.data.isNotEmpty()) {
                        heroes.value = result.data
                    } else {
                        showError.value = true
                    }
                }
                is DataResult.Error -> {
                    showError.value = true
                }
            }
        }
    }
}
