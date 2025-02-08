package com.example.mykotlin.DogApiHandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mykotlin.R

class DogActivity : AppCompatActivity() {

    private lateinit var factsTextView: TextView
    private val viewModel: DogFactsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)
        factsTextView = findViewById(R.id.factsTextView)
        val loadFactsButton: Button = findViewById(R.id.loadFactsButton)

        loadFactsButton.setOnClickListener {
            viewModel.fetchDogFacts()
        }

        viewModel.facts.observe(this, Observer { facts ->
            factsTextView.text = facts
        })
    }
}