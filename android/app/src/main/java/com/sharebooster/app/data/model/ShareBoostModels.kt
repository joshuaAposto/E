package com.sharebooster.app.data.model

import com.google.gson.annotations.SerializedName

data class ShareBoostRequest(
    @SerializedName("cookie")
    val cookie: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("interval")
    val interval: Int
)

data class ShareStatusResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("hasActiveTask")
    val hasActiveTask: Boolean,
    @SerializedName("taskStatus")
    val taskStatus: String?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("target")
    val target: Int?,
    @SerializedName("postId")
    val postId: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("startTime")
    val startTime: Long?,
    @SerializedName("runtime")
    val runtime: Long?
)

data class ShareLogsResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("logs")
    val logs: List<ShareLog>,
    @SerializedName("hasActiveTask")
    val hasActiveTask: Boolean
)

data class ShareLog(
    @SerializedName("type")
    val type: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("target")
    val target: Int?,
    @SerializedName("postId")
    val postId: String?,
    @SerializedName("timestamp")
    val timestamp: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("reason")
    val reason: String?
)