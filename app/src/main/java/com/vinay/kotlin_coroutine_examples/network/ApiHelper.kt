package com.vinay.kotlin_coroutine_examples.network

import com.vinay.kotlin_coroutine_examples.model.UserModel

interface ApiHelper {

    suspend fun getUsers() : List<UserModel>

    suspend fun getMoreUsers() :List<UserModel>

    suspend fun getUsersWithError() : List<UserModel>
}