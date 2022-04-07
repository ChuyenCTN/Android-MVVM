package com.example.android_mvvm

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm.base.BaseActivity

class MainActivity : BaseActivity() {

    var mainViewModel: MainViewModel = MainViewModel()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun setupView(savedInstanceState: Bundle?) {
        mainViewModel.getData()
//        mainViewModel.isLoading.observe(this) {
//            Log.d("TAG", it.toString())
//        }
    }
}