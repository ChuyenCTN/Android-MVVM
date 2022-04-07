package com.example.android_mvvm.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoadMore = MutableLiveData<Boolean>()
    val isLoadMore: LiveData<Boolean> = _isLoadMore

    private val _isNoData = MutableLiveData<Boolean>()
    val isNoData: LiveData<Boolean> = _isNoData

    fun showLoading(isLoading: Boolean = true) {
        _isLoading.postValue(isLoading)
    }

    fun showLoadMore(isLoadMore: Boolean = true) {
        _isLoadMore.postValue(isLoadMore)
    }


}