package com.example.mykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import com.example.mykotlin.databinding.ActivityCrucialPageBinding

class CrucialPage : AppCompatActivity() {

    private lateinit var  binding: ActivityCrucialPageBinding

    private lateinit var viewPager: ViewPager2
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private lateinit var images: List<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrucialPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        binding.floatingActionButton.setOnClickListener{
            startActivity(Intent(this@CrucialPage,WritingTask::class.java))
        }


        images = listOf(
            R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4,R.drawable.image5, R.drawable.image6,R.drawable.image7,
            R.drawable.image8
        )

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ImageAdapter(images)

        // Start auto slide
        startAutoSlide()
    }

    private fun startAutoSlide() {
        val runnable = object : Runnable {
            override fun run() {
                if (currentPage >= images.size) {
                    currentPage = 0
                }
                viewPager.setCurrentItem(currentPage++, true)
                handler.postDelayed(this, 1000) // Change every 1 second
            }
        }
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Stop auto-slide when activity is destroyed
    }
}