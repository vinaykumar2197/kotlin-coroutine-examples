package com.vinay.kotlin_coroutine_examples.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinay.kotlin_coroutine_examples.network.ApiHelper
import com.vinay.kotlin_coroutine_examples.room.DatabaseHelper
import com.vinay.kotlin_coroutine_examples.ui.SingleNetwork.SingleNetworkCallViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper?) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}