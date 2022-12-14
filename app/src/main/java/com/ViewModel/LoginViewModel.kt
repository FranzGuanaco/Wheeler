package com.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    var num = 1


    val currentnum: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val currentbool: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


    fun essai (): Int {
        num++
        return num
    }
}