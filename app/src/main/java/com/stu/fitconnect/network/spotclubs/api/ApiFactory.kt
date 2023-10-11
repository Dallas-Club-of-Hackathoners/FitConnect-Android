package com.stu.fitconnect.network.spotclubs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    const val BASE_URL = "http://5.35.101.92:8080/"
    const val API_KEY = "BuildConfig.API_KEY"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService: SportClubsApiService = retrofit.create(SportClubsApiService::class.java)
}