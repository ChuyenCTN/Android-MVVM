package com.example.android_mvvm.data.model.auth

data class AuthSipRequest(
    val userName: String,
    val passWord: String,
    val domain: String,
    val transportType: String
)