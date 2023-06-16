package com.candroid.candroidrcc026

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Callable {

    @GET("/v2/top-headlines?apiKey=dd2ef6fd2f2d4f5fbdf177785e4c2d5b")
    fun getNews(@Query("category") cat: String, @Query("country") code: String): Call<NewsModel>

}