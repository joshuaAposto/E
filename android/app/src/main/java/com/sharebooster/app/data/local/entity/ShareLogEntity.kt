package com.sharebooster.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "share_logs")
data class ShareLogEntity(
    @PrimaryKey
    val logId: String,
    val sessionId: String,
    val userId: String,
    val type: String, // session_start, share_log, share_error, session_end
    val message: String?,
    val count: Int?,
    val target: Int?,
    val postId: String?,
    val timestamp: String,
    val success: Boolean?,
    val reason: String?
)