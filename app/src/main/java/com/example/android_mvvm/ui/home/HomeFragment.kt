package com.example.android_mvvm.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.android_mvvm.R
import com.example.android_mvvm.base.BaseFragment
import com.example.android_mvvm.base.BaseViewModel
import com.example.android_mvvm.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

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
        homeViewModel.isLoading.observe(this) {
            if (it) {
               binding .textHome.text = "bfdhbfh"

                Log.d("HomeFragment", "viewModel.isLoading.toString()")
            } else {
                binding.textHome.text = "false"

                Log.d("HomeFragment", "false")
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
    }
}