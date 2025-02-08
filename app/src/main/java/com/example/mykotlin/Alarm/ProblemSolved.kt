package com.example.mykotlin.Alarm

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import com.example.mykotlin.R

class ProblemSolved : AppCompatActivity() {


    private lateinit var numbertext:EditText
    private  lateinit var button : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_solved)
        numbertext = findViewById(R.id.numbertext);
        button = findViewById(R.id.button)


        val text = numbertext.text.toString().trim()

        button.setOnClickListener{
            if (text.equals("10")){
                var mediaPlayer: MediaPlayer
                mediaPlayer = MediaPlayer.create(this, R.raw.alarmsound)
                mediaPlayer.start()
            }
        }





    }
}