package com.example.android_mvvm.ui.home

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_mvvm.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment11111111"
    }
    val text: LiveData<String> = _text


    fun getData() {
        showLoading(true)
        Handler().postDelayed({
            showLoading(false)
        }, 2000)
    }
}