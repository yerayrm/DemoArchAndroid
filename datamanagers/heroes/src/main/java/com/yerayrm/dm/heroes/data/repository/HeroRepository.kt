package com.yerayrm.dm.heroes.data.repository

import com.yerayrm.dm.heroes.data.datasource.ApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeroRepository(
    private val apiDataSource: ApiDataSource
) {
    suspend fun getHeroes() = withContext(Dispatchers.IO) {
        apiDataSource.getHeroes()
    }
}
