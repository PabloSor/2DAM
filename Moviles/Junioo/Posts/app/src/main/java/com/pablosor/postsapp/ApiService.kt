package com.pablosor.postsapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("https://jsonplaceholder.typicode.com/posts")
    suspend fun getPosts():Response<List<PostResponse>>
}