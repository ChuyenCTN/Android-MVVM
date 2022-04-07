package com.example.android_mvvm

import android.os.Handler
import androidx.lifecycle.ViewModel
import com.example.android_mvvm.base.BaseViewModel
import kotlinx.coroutines.Delay

class MainViewModel : BaseViewModel() {

    fun getData() {
        showLoading(true)
        Handler().postDelayed({
            showLoading(false)
        }, 2000)
    }

}