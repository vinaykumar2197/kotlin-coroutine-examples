package com.vinay.kotlin_coroutine_examples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.vinay.kotlin_coroutine_examples.databinding.ActivityMainBinding
import com.vinay.kotlin_coroutine_examples.ui.SingleNetwork.MovieSearchActivity
import com.vinay.kotlin_coroutine_examples.ui.SingleNetwork.SingleNetworkCallActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun startSingleNetworkCallActivity(view : View){
//        startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
        startActivity(Intent(this@MainActivity, MovieSearchActivity::class.java))
    }


    fun startSeriesNetworkCallsActivity(view : View){

    }


    fun startParallelNetworkCallsActivity(view : View){

    }


    fun startRoomDatabaseActivity(view : View){

    }


    fun startTimeoutActivity(view : View){

    }


    fun startTryCatchActivity(view : View){

    }


    fun startExceptionHandlerActivity(view : View){

    }


    fun startIgnoreErrorAndContinueActivity(view : View){

    }


    fun startLongRunningTaskActivity(view : View){

    }


    fun startTwoLongRunningTasksActivity(view : View){

    }

}