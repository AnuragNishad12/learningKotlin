package com.example.mykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mykotlin.databinding.ActivityCrucialPageBinding

class CrucialPage : AppCompatActivity() {

    private lateinit var  binding: ActivityCrucialPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrucialPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        binding.floatingActionButton.setOnClickListener{
            startActivity(Intent(this@CrucialPage,WritingTask::class.java))
        }

    }
}