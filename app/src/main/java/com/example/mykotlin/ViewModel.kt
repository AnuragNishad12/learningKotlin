package com.example.mykotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow


class myViewModel : ViewModel(){

   val _name = MutableLiveData<String> ()

    val name : LiveData<String>
    get() = _name

    fun setname(newName : String){
    _name.value = newName
    }

}