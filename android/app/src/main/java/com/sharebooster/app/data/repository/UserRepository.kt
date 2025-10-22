package com.sharebooster.app.data.repository

import com.sharebooster.app.data.local.dao.UserDao
import com.sharebooster.app.data.local.entity.UserEntity
import com.sharebooster.app.data.model.*
import com.sharebooster.app.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    
    suspend fun getUserProfile(): Result<UserResponse> {
        return try {
            val response = apiService.getUserProfile("Bearer ${getStoredToken()}")
            if (response.isSuccessful) {
                val userResponse = response.body()
                if (userResponse != null) {
                    // Update local user data
                    saveUserToLocal(userResponse.user)
                    Result.success(userResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to get user profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateProfile(
        fullname: String? = null,
        username: String? = null,
        email: String? = null,
        removePfp: Boolean = false
    ): Result<UserResponse> {
        return try {
            val request = UpdateProfileRequest(
                fullname = fullname,
                username = username,
                email = email,
                removePfp = removePfp
            )
            val response = apiService.updateProfile("Bearer ${getStoredToken()}", request)
            if (response.isSuccessful) {
                val userResponse = response.body()
                if (userResponse != null) {
                    // Update local user data
                    saveUserToLocal(userResponse.user)
                    Result.success(userResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to update profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun changePassword(
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): Result<ApiResponse> {
        return try {
            val request = ChangePasswordRequest(currentPassword, newPassword, confirmNewPassword)
            val response = apiService.changePassword("Bearer ${getStoredToken()}", request)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    Result.success(apiResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to change password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun requestPremium(plan: String): Result<ApiResponse> {
        return try {
            val request = PremiumRequest(plan)
            val response = apiService.requestPremium("Bearer ${getStoredToken()}", request)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    Result.success(apiResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to request premium"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getUserFlow(userId: String): Flow<UserEntity?> {
        return userDao.getUserByIdFlow(userId)
    }
    
    suspend fun getUserById(userId: String): UserEntity? {
        return userDao.getUserById(userId)
    }
    
    suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }
    
    private suspend fun saveUserToLocal(user: User) {
        val userEntity = UserEntity(
            userId = user.userId,
            username = user.username,
            fullname = user.fullname,
            email = user.email,
            pfpUrl = user.pfpUrl,
            isPremium = user.isPremium,
            premiumExpiration = user.premiumExpiration,
            isAdmin = user.isAdmin,
            status = user.status,
            createdAt = user.createdAt
        )
        userDao.insertUser(userEntity)
    }
    
    private fun getStoredToken(): String? {
        // This should be implemented using SharedPreferences or DataStore
        // For now, returning null - will be implemented in the ViewModel
        return null
    }
}