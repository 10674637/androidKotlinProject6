package com.example.tc_project6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(IO).launch {
            getData()
        }
    }
    suspend fun getData(){
        withContext(IO){
            //Network IO goes here
            Log.i("cs3680", " simulated network I/O")
            withContext(Main){
                Log.i("CS3690", "simulate GUI update")
            }
        }
    }
}

