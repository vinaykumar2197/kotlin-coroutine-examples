package com.vinay.kotlin_coroutine_examples.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
//    private const val BASE_URL ="https://5e510330f2c0d300147c034c.mockapi.io/"
    public const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w300/"
    public const val API_KEY = "3f8a524ab853b6b302347180256934cd"
    public const val BASE_URL = "https://api.themoviedb.org/3/"

    //Category
    public const val EP_TOP_RATED = "movie/top_rated"
    public const val EP_SEARCH = "search/movie"

    fun getRetrofit() : Retrofit {
        var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    val apiInterface = getRetrofit().create(ApiInterface::class.java)


}