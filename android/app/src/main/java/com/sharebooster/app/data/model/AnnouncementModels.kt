package com.sharebooster.app.data.model

import com.google.gson.annotations.SerializedName

data class Announcement(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("created_by_username")
    val createdByUsername: String,
    @SerializedName("created_at")
    val createdAt: String
)

data class CreateAnnouncementRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("type")
    val type: String
)