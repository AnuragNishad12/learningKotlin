package com.example.mykotlin.DogApiHandling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class DogFactsViewModel : ViewModel() {
    private val repository = DogFactsRepository()

    private val _facts = MutableLiveData<String>()
    val facts: LiveData<String> = _facts

    fun fetchDogFacts() {
        viewModelScope.launch {
            val response = repository.getDogFacts(5)
            response?.let {
                _facts.value = it.facts.joinToString("\n\n")
            }
        }
    }
}