package com.sharebooster.app.data.model

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("remove_pfp")
    val removePfp: Boolean? = false
)

data class ChangePasswordRequest(
    @SerializedName("currentPassword")
    val currentPassword: String,
    @SerializedName("newPassword")
    val newPassword: String,
    @SerializedName("confirmNewPassword")
    val confirmNewPassword: String
)