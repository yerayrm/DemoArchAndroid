package com.yerayrm.dm.heroes.data.datasource

import com.yerayrm.dm.heroes.BuildConfig
import com.yerayrm.dm.heroes.model.Hero
import com.yerayrm.dm.heroes.model.HeroesResponse
import com.yerayrm.dm.heroes.utils.DataResult
import java.lang.Exception
import java.math.BigInteger
import java.security.MessageDigest

open class ApiDataSource(
    private val apiService: ApiService
) {
    suspend fun getHeroes(): DataResult<List<Hero>> {
        val publicApiKey = BuildConfig.MarvelPublicKey
        val privateApiKey = BuildConfig.MarvelPrivateKey
        val ts = System.currentTimeMillis()

        return try {
            val hash = generateHash(ts, privateApiKey, publicApiKey)

            val response = apiService.getCharacters(
                ORDER_BY,
                LIMIT,
                publicApiKey,
                ts,
                hash
            )

            response.data?.results?.let {
                DataResult.Success(it)
            } ?: DataResult.Error(Exception("Unexpected error"))
        } catch (exception: Exception) {
            DataResult.Error(exception)
        }
    }

    private fun generateHash(ts: Long, privateKey: String, publicKey: String): String {
        val md = MessageDigest.getInstance("MD5")
        val message = ts.toString() + privateKey + publicKey
        return BigInteger(1,
            md.digest(message.toByteArray())).toString(16).padStart(32, '0')
    }

    companion object {
        private const val ORDER_BY = "name"
        private const val LIMIT = 20
    }
}