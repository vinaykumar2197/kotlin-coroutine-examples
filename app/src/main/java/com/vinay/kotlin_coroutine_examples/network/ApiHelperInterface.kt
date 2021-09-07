package com.vinay.kotlin_coroutine_examples.network

import com.vinay.kotlin_coroutine_examples.model.MovieResponse
import com.vinay.kotlin_coroutine_examples.model.UserModel
import com.vinay.kotlin_coroutine_examples.network.RetrofitBuilder.API_KEY

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

    override suspend fun getTopRatedMovies(query: String, page: Int, perPage: Int): MovieResponse? {
//        return  apiInterface.getTopRatedMovies(page,API_KEY)
        return  apiInterface.getSearchedMovies(page,API_KEY,query)
    }


}