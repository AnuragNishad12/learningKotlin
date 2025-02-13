package com.example.mykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mykotlin.databinding.ActivityWritingTaskBinding

class WritingTask : AppCompatActivity() {

    private lateinit var binding: ActivityWritingTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritingTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()




    }
}