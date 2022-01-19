package com.example.coroutineexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // main()

        println("blocked start")
        runBlocking {
            launch {
                delay(40)
                println("runBlocking")
            }
        }

        println("runFinish")
        GlobalScope.launch {
            delay(4000)
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
            println("GlobalScope")
        }
        println("GlobalScope finish")
        GlobalScope.launch {
            launch(Dispatchers.Main){
                println("Main Thread      : ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Default) {
                println("Default Thread   : ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Unconfined){
                println("Unconfined Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.IO) {
                println("IO Thread        : ${Thread.currentThread().name}")
            }
        }
    }

    fun main() = runBlocking {
        repeat(100_000) { // launch a lot of coroutines
            launch {
                delay(50L)
                print(".xcvvxcv")
            }
        }
    }
}