package com.vinay.kotlin_coroutine_examples.network

import com.vinay.kotlin_coroutine_examples.model.UserModel

class ApiHelperInterface (private val apiInterface: ApiInterface)  : ApiHelper{

    override suspend fun getUsers(): List<UserModel> {
        return  apiInterface.getUsers()
    }

    override suspend fun getMoreUsers(): List<UserModel> {
        return  apiInterface.getMoreUsers()
    }

    override suspend fun getUsersWithError(): List<UserModel> {
        return  apiInterface.getUsersWithError()
    }


}