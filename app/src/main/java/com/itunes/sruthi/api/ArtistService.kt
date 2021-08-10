package com.itunes.sruthi.api

import com.itunes.sruthi.model.ArtistData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {

    @GET("search")
    suspend fun getArtistTracks(@Query("term") artistName: String): ArtistData

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/"

        fun create(): ArtistService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArtistService::class.java)
        }
    }
}
