package com.example.finalprojectgg.network

import com.example.finalprojectgg.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}
