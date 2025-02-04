package com.example.finalprojectgg.network

import com.example.finalprojectgg.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface FakeStoreApi {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}
