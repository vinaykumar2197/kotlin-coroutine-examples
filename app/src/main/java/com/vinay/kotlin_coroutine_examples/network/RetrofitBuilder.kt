package com.vinay.kotlin_coroutine_examples.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL ="https://5e510330f2c0d300147c034c.mockapi.io/"

    fun getRetrofit() : Retrofit {
        var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    val apiInterface = getRetrofit().create(ApiInterface::class.java)


}