package com.sharebooster.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "share_sessions")
data class ShareSessionEntity(
    @PrimaryKey
    val sessionId: String,
    val userId: String,
    val url: String,
    val postId: String?,
    val amount: Int,
    val interval: Int,
    val currentCount: Int,
    val targetCount: Int,
    val status: String, // running, completed, stopped, error
    val startTime: Long,
    val endTime: Long?,
    val lastUpdated: Long = System.currentTimeMillis()
)