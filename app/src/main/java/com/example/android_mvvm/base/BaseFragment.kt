package com.example.android_mvvm.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    private val TAG = BaseFragment::class.java.simpleName
    private var mViewModel: BaseViewModel? = null

    abstract fun getRootLayoutId(): Int
    abstract fun getViewModel(): BaseViewModel
    abstract fun setupUI(view: View)

    private lateinit var mViewDataBinding: B
    val binding: B get() = mViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel = getViewModel()
        mViewDataBinding = DataBindingUtil.inflate(inflater, getRootLayoutId(), container, false)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
        return mViewDataBinding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view !is EditText) {
            view?.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }
        setupUI(view)

        mViewModel.let { viewModel ->
            viewModel?.isLoading?.observe(viewLifecycleOwner) {
                if (it)
                    Log.d(TAG, "viewModel.isLoading.toString()")
                else
                    Log.d(TAG, "false")
            }
        }
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}