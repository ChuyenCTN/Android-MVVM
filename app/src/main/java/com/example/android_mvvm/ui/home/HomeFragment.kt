package com.example.android_mvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm.R
import com.example.android_mvvm.base.BaseFragment
import com.example.android_mvvm.base.BaseViewModel
import com.example.android_mvvm.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private var homeViewModel: HomeViewModel = HomeViewModel()


    override fun getRootLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModel(): BaseViewModel {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        return homeViewModel
    }

    override fun setupUI(view: View) {
        homeViewModel.getData()
        homeViewModel.text.observe(this) {
            _binding?.textHome?.text = it
        }
    }

    override fun setUpdateBinding(inflater: LayoutInflater, container: ViewGroup?) {
       _binding = FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}