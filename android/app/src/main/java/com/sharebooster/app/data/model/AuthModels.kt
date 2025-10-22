package com.sharebooster.app.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)

data class RegisterRequest(
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirmPassword")
    val confirmPassword: String
)

data class GoogleLoginRequest(
    @SerializedName("credential")
    val credential: String
)

data class AuthResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User?,
    @SerializedName("trialGranted")
    val trialGranted: Boolean? = false,
    @SerializedName("isNewUser")
    val isNewUser: Boolean? = false
)

data class User(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("pfp_url")
    val pfpUrl: String?,
    @SerializedName("isPremium")
    val isPremium: Boolean,
    @SerializedName("premiumExpiration")
    val premiumExpiration: String?,
    @SerializedName("isAdmin")
    val isAdmin: Boolean = false,
    @SerializedName("status")
    val status: String = "active",
    @SerializedName("createdAt")
    val createdAt: String? = null
)

data class UserResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: User
)

data class ApiResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("error")
    val error: String?
)