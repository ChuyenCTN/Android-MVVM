package com.example.android_mvvm.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.MyApplication
import com.example.android_mvvm.data.model.auth.AuthRequest
import com.example.android_mvvm.data.model.auth.AuthResponse
import com.example.android_mvvm.data.model.auth.CustommerResponse
import com.example.android_mvvm.data.model.response.PriorityResponse
import com.example.android_mvvm.data.model.response.StateResponse
import com.example.android_mvvm.data.model.response.TypeResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


import java.lang.reflect.Type

object Key {
    const val USER_INFO_RESPONSE = "data_user_info_response"
    const val USER_INFO_REQUEST = "data_user_info_request"
    const val CUSTOMER_INFO_RESPONSE = "data_customer_response"
    const val TOKEN = "data_token"
    const val DOMAIN = "tenant_code"
}

class SharePreferenceUtils private constructor() {
    val name = "NextCrmCallCenterDI"

    init {
        mPrefs = MyApplication.context?.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    companion object {
        private var mPrefs: SharedPreferences? = null
        private var instance: SharePreferenceUtils = SharePreferenceUtils()
        fun getInstances(): SharePreferenceUtils {
            return instance
        }
    }

    fun saveString(key: String, value: String?) {
        mPrefs?.edit()?.putString(key, value)?.apply()
    }

    fun removeString(key: String) {
        mPrefs?.edit()?.remove(key)?.apply()
    }

    fun getString(key: String): String? {
        return mPrefs?.getString(key, "")
    }

    fun saveInt(key: String, content: Int?) {
        content?.let {
            mPrefs?.edit()?.putInt(key, content)?.apply()
        }
    }

    fun getInt(key: String): Int {
        return mPrefs?.getInt(key, -1) ?: -1
    }

    fun saveBoolean(key: String, value: Boolean?) {
        value?.let {
            mPrefs?.edit()?.putBoolean(key, value)?.apply()
        }
    }

    fun getBoolean(key: String): Boolean? {
        return mPrefs?.getBoolean(key, false)
    }

    fun saveToken(token: String?) {
        mPrefs?.edit()?.putString(Key.TOKEN, token)?.apply()
    }

    fun getToken(): String {
        return mPrefs?.getString(Key.TOKEN, "").toString()
    }

    fun saveDomain(domain: String?) {
        mPrefs?.edit()?.putString(Key.DOMAIN, domain)?.apply()
    }

    fun getDomain(): String {
        return mPrefs?.getString(Key.DOMAIN, "").toString()
    }

    fun saveAuthRequest(authRequest: AuthRequest?) {
        authRequest.let {
            mPrefs?.edit()?.putString(Key.USER_INFO_REQUEST, Gson().toJson(it))?.apply()
        }
    }

    fun getAuthRequest(): AuthRequest? {
        val json = mPrefs?.getString(Key.USER_INFO_REQUEST, null) ?: return null
        return Gson().fromJson(json, AuthRequest::class.java)
    }

    fun saveAuthResponse(authResponse: AuthResponse?) {
        authResponse.let {
            mPrefs?.edit()?.putString(Key.USER_INFO_RESPONSE, Gson().toJson(it))?.apply()
        }
    }

    fun getAuthResponse(): AuthResponse {
        val json = mPrefs?.getString(Key.USER_INFO_RESPONSE, null)
        return Gson().fromJson(json, AuthResponse::class.java)
    }

    fun saveCustomerResponse(tenantcode: CustommerResponse?) {
        tenantcode.let {
            mPrefs?.edit()?.putString(Key.CUSTOMER_INFO_RESPONSE, Gson().toJson(it))?.apply()
        }
    }

    fun getCustomerResponse(): CustommerResponse? {
        val json = mPrefs?.getString(Key.CUSTOMER_INFO_RESPONSE, null)
        return Gson().fromJson(json, CustommerResponse::class.java)
    }

    fun saveStateList(keyType: String, data: List<StateResponse>) {
        data.let {
            mPrefs?.edit()?.putString(keyType, Gson().toJson(it))?.apply()
        }
    }

    fun getStateList(keyType: String): List<StateResponse>? {
        val json = mPrefs?.getString(keyType, null)
        val type: Type = object : TypeToken<List<StateResponse?>?>() {}.type
        return Gson().fromJson(json, type)
    }

    fun saveTypeList(keyType: String, data: List<TypeResponse>) {
        data.let {
            mPrefs?.edit()?.putString(keyType, Gson().toJson(it))?.apply()
        }
    }

    fun getTypeList(keyType: String): List<TypeResponse>? {
        val json = mPrefs?.getString(keyType, null)
        val type: Type = object : TypeToken<List<TypeResponse?>?>() {}.type
        return Gson().fromJson(json, type)
    }

    fun savePriorityList(keyType: String, data: List<PriorityResponse>) {
        data.let {
            mPrefs?.edit()?.putString(keyType, Gson().toJson(it))?.apply()
        }
    }

    fun getPriorityList(keyType: String): List<PriorityResponse>? {
        val json = mPrefs?.getString(keyType, null)
        val type: Type = object : TypeToken<List<PriorityResponse?>?>() {}.type
        return Gson().fromJson(json, type)
    }

    fun logout() {
        val domain = getDomain()
        mPrefs?.edit()?.clear()?.apply()
        saveDomain(domain)
    }
}