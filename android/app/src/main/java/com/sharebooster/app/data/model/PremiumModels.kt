package com.sharebooster.app.data.model

import com.google.gson.annotations.SerializedName

data class PremiumRequest(
    @SerializedName("plan")
    val plan: String
)

data class PremiumRequestData(
    @SerializedName("requestId")
    val requestId: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("planRequested")
    val planRequested: String,
    @SerializedName("requestTimestamp")
    val requestTimestamp: String,
    @SerializedName("status")
    val status: String
)

data class UpdatePremiumRequest(
    @SerializedName("isPremium")
    val isPremium: Boolean,
    @SerializedName("expirationDate")
    val expirationDate: String?
)

data class UpdateStatusRequest(
    @SerializedName("status")
    val status: String
)