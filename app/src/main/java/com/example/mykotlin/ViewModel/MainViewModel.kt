package com.example.mykotlin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // MutableLiveData to hold a counter value
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter

    init {
        _counter.value = 0 // Initial value
    }

    // Method to increase the counter
    fun increaseCounter() {
        _counter.value = (_counter.value ?: 0) + 1
    }

    // Method to decrease the counter
    fun decreaseCounter() {
        _counter.value = (_counter.value ?: 0) - 1
    }
}

