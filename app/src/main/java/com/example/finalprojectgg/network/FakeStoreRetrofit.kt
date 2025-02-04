package com.example.finalprojectgg.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FakeStoreRetrofit {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val api: FakeStoreApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeStoreApi::class.java)
    }
}
