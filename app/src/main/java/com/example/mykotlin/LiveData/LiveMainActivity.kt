package com.example.mykotlin.LiveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mykotlin.R

class LiveMainActivity : AppCompatActivity() {
    // MutableLiveData to hold the data
//    private val _textLiveData = MutableLiveData<String>()
//    val textLiveData: LiveData<String> = _textLiveData

    //this line store the data when changes
    private val _textdata = MutableLiveData<String>()

    //this is read the data from the MutableLiveData
    val textdata :LiveData<String> = _textdata



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_main)
        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

//        // Observe LiveData
//        textLiveData.observe(this, Observer { newText ->
//            textView.text = newText
//        })
//
//        // Change LiveData value on button click
//        button.setOnClickListener {
//            _textLiveData.value = "LiveData Updated!"
//        }

        textdata.observe(this, Observer {
            newtext-> textView.text = newtext
        })

        button.setOnClickListener{
            _textdata.value = "Updating UI"
        }

    }
}