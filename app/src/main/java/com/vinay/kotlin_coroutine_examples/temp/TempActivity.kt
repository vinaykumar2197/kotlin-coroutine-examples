package com.vinay.kotlin_coroutine_examples.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.vinay.kotlin_coroutine_examples.R
import kotlinx.coroutines.*

class TempActivity : AppCompatActivity() {

    private val customScopeName = CoroutineScope(CoroutineName("Coroutine_MI6"))

    private val customScopeNameDispatcher = CoroutineScope(Dispatchers.IO+CoroutineName("Coroutine_MI21"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        GlobalScope.launch {
            while (true){
                delay(1000L)
                Log.d(TAG, "GlobalScope: Running")
            }
        }

        lifecycleScope.launch {
            Log.e(Companion.TAG, "lifecycleScope: "+ this.coroutineContext.toString())
            launch {
                Log.e(Companion.TAG, "lifecycleScope: launch "+ this.coroutineContext.toString())
            }
        }

        customScopeName.launch {
            Log.e(Companion.TAG, "customScopeName: "+ this.coroutineContext.toString())
        }

        customScopeNameDispatcher.launch {
            Log.e(Companion.TAG, "customScopeNameDispatcher: "+ this.coroutineContext.toString())
        }

    }

    companion object {
        private const val TAG = "TempActivity"
    }
}