package com.example.android_mvvm

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm.base.BaseActivity
import com.example.android_mvvm.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    var mainViewModel: MainViewModel = MainViewModel()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun setupView(savedInstanceState: Bundle?) {
        mainViewModel.getData()
        mainViewModel.isLoading.observe(this) {
            Log.d("TAG", it.toString())
            binding.tvMainName.text = it.toString()
        }
    }
}