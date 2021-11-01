package com.yerayrm.dm.heroes.data.datasource

import com.yerayrm.dm.heroes.model.HeroesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") ts: Long,
        @Query("hash") hash: String
    ): HeroesResponse

    companion object {
        private const val URL = "https://gateway.marvel.com:443"
        private const val TIMEOUT: Long = 30

        fun create(): ApiService {
            // Logging level
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            // Create OkHttpClient with timeout
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)

            // Set log interceptor
            httpClient.addInterceptor(logging)

            // Build OkHttpClient instance
            val client = httpClient.build()

            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService::class.java)
        }
    }
}
