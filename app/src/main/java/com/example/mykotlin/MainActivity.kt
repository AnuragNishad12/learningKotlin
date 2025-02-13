package com.example.mykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        val textView = findViewById<TextView>(R.id.textView)
        val fullText = "Hello Beautiful :)"
        val delayMillis: Long = 100

        typeWriterEffect(textView, fullText, delayMillis)


    }


    private fun typeWriterEffect(textView: TextView, text: String, delay: Long) {
        val handler = Handler(Looper.getMainLooper())
        var index = 0

        val runnable = object : Runnable {
            override fun run() {
                if (index <= text.length) {
                    textView.text = text.substring(0, index)
                    index++
                    handler.postDelayed(this, delay)
                } else {
                    handler.postDelayed({
                        startActivity(Intent(this@MainActivity, LandingPage::class.java))
                        finish()
                    }, 1000)
                }
            }
        }
        handler.post(runnable)
    }
}