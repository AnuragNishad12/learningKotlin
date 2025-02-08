package com.example.mykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewCoroutines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_coroutines)


        lifecycleScope.launch {
            println("Task started on thread: ${Thread.currentThread().name}")

            // Simulate a background task
            val result = withContext(Dispatchers.IO) {
                println("Performing task on thread: ${Thread.currentThread().name}")
                simulateTask()
            }

            // Update UI with the result
            println("Task result: $result")
        }
    }

    private suspend fun simulateTask(): String {
        delay(2000) // Simulating a delay of 2 seconds
        return "Task Complete"
    }
}