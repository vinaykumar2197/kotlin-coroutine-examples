package com.vinay.kotlin_coroutine_examples.network

import com.vinay.kotlin_coroutine_examples.model.UserModel
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getUsers() : List<UserModel>

    @GET("more-users")
    suspend fun getMoreUsers() :List<UserModel>

    @GET("error")
    suspend fun getUsersWithError() : List<UserModel>

}