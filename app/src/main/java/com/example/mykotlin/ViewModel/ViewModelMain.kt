package com.example.mykotlin.ViewModel


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import androidx.activity.viewModels

import androidx.lifecycle.Observer
import com.example.mykotlin.R

class ViewModelMain : AppCompatActivity() {

    // Get ViewModel instance using 'viewModels' delegate
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_main)


        val textCounter: TextView = findViewById(R.id.textCounter)
        val buttonIncrease: Button = findViewById(R.id.buttonIncrease)
        val buttonDecrease: Button = findViewById(R.id.buttonDecrease)

        // Observe LiveData and update UI when data changes
        viewModel.counter.observe(this, Observer { count ->
            textCounter.text = count.toString()
        })

        // Set click listeners to update ViewModel data
        buttonIncrease.setOnClickListener {
            viewModel.increaseCounter()
        }

        buttonDecrease.setOnClickListener {
            viewModel.decreaseCounter()
        }

    }
}