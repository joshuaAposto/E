package com.sharebooster.app.data.model

import com.google.gson.annotations.SerializedName

data class ApiKeyResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("apiKey")
    val apiKey: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("message")
    val message: String?
)

data class SaveCookieRequest(
    @SerializedName("cookie")
    val cookie: String
)

data class CookieResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("cookie")
    val cookie: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("message")
    val message: String?
)