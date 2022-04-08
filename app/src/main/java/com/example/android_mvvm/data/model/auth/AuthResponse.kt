package com.example.android_mvvm.data.model.auth

import com.example.android_mvvm.data.model.auth.User
import com.google.gson.annotations.SerializedName

open class AuthResponse(
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)