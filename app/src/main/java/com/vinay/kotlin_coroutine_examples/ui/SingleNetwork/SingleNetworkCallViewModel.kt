package com.vinay.kotlin_coroutine_examples.ui.SingleNetwork

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinay.kotlin_coroutine_examples.model.UserModel
import com.vinay.kotlin_coroutine_examples.network.ApiHelper
import com.vinay.kotlin_coroutine_examples.room.DatabaseHelper
import com.vinay.kotlin_coroutine_examples.util.Resource
import kotlinx.coroutines.launch

class SingleNetworkCallViewModel(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper?)
    : ViewModel()
{

    private val users = MutableLiveData<Resource<List<UserModel>>>()

    init {
        fetchUsers()
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