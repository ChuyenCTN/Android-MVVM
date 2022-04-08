package com.example.android_mvvm.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.android_mvvm.R
import com.hosco.nextcrm.callcenter.common.Const

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    private var isBackPressed: Boolean = false
    private val TAG = BaseActivity::class.java.simpleName
    private var mViewModel: BaseViewModel? = null

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): BaseViewModel
    abstract fun setupView(savedInstanceState: Bundle?)

    private lateinit var mViewDataBinding: B

    val binding: B get() = mViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
        overridePendingTransition(R.anim.anim_right_to_left_enter, R.anim.anim_right_to_left_leave)
        mViewModel = getViewModel()
//        setContentView(getLayoutId())
        setupView(savedInstanceState)

//        val window: Window = window
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = getColor(R.color.colorPressButton)

        mViewModel.let { viewModel ->
            viewModel?.isLoading?.observe(this) {
                if (it)
                    Log.d(TAG, "viewModel.isLoading.toString()")
                else
                    Log.d(TAG, "false")
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        if (parent !is EditText) {
            parent?.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }
        return super.onCreateView(parent, name, context, attrs)
    }


    fun goToActivity(c: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(this, c)
        bundle?.let {
            intent.putExtra(Const.KEY_EXTRA_DATA, bundle)
        }
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    fun replaceFragment(viewID: Int, fragment: Fragment?, tag: String? = null) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(viewID, fragment!!)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onBackPressed() {
        if (!isTaskRoot) {
            super.onBackPressed()
            overridePendingTransition(
                R.anim.anim_left_to_right_enter,
                R.anim.anim_left_to_right_leave
            )
            return
        }
        if (isBackPressed) {
            super.onBackPressed()
            overridePendingTransition(
                R.anim.anim_left_to_right_enter,
                R.anim.anim_left_to_right_leave
            )
        } else {
            Toast.makeText(this, R.string.back_again_to_exit, Toast.LENGTH_SHORT).show()
            isBackPressed = true
            Handler().postDelayed({ isBackPressed = false }, 2000)
        }
    }

}