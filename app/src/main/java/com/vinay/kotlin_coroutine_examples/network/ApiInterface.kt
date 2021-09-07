package com.vinay.kotlin_coroutine_examples.network

import com.vinay.kotlin_coroutine_examples.model.MovieResponse
import com.vinay.kotlin_coroutine_examples.model.UserModel
import com.vinay.kotlin_coroutine_examples.network.RetrofitBuilder.EP_SEARCH
import com.vinay.kotlin_coroutine_examples.network.RetrofitBuilder.EP_TOP_RATED
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("users")
    suspend fun getUsers() : List<UserModel>

    @GET("more-users")
    suspend fun getMoreUsers() :List<UserModel>

    @GET("error")
    suspend fun getUsersWithError() : List<UserModel>

    @GET(EP_TOP_RATED)
    suspend fun getTopRatedMovies(@Query("page") page: Int, @Query("api_key") apiKey: String?): MovieResponse?

    @GET(EP_SEARCH)
    suspend fun getSearchedMovies(@Query("page") page: Int, @Query("api_key") apiKey: String?, @Query("query") query: String?,): MovieResponse?


}