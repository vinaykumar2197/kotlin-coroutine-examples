package com.vinay.kotlin_coroutine_examples.ui.SingleNetwork

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.vinay.kotlin_coroutine_examples.model.UserModel
import com.vinay.kotlin_coroutine_examples.network.ApiHelper
import com.vinay.kotlin_coroutine_examples.paging.MoviePagingSource
import com.vinay.kotlin_coroutine_examples.room.DatabaseHelper
import com.vinay.kotlin_coroutine_examples.util.Resource
import kotlinx.coroutines.launch
import java.net.URLEncoder

class SingleNetworkCallViewModel(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper?)
    : ViewModel()
{

    private val users = MutableLiveData<Resource<List<UserModel>>>()

//    init {
//        fetchUsers()
//    }

    private val query = MutableLiveData<String>()
    init {
        query.value = "Test"
    }

    val list =
        Pager(PagingConfig(pageSize = 10)) {
            MoviePagingSource(apiHelper, "")
        }.liveData.cachedIn(viewModelScope)


    val listSearchedData = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MoviePagingSource(apiHelper, URLEncoder.encode(query, "utf-8"))
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }



    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromApi = apiHelper.getUsers()
                users.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<UserModel>>> {
        return users
    }



}