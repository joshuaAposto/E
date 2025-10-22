package com.sharebooster.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val userId: String,
    val username: String,
    val fullname: String,
    val email: String,
    val pfpUrl: String?,
    val isPremium: Boolean,
    val premiumExpiration: String?,
    val isAdmin: Boolean,
    val status: String,
    val createdAt: String?,
    val lastUpdated: Long = System.currentTimeMillis()
)