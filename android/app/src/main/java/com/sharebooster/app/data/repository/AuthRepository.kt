package com.sharebooster.app.data.repository

import com.sharebooster.app.data.local.dao.UserDao
import com.sharebooster.app.data.local.entity.UserEntity
import com.sharebooster.app.data.model.*
import com.sharebooster.app.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    
    suspend fun login(username: String, password: String): Result<AuthResponse> {
        return try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                val authResponse = response.body()
                if (authResponse != null && authResponse.user != null) {
                    // Save user to local database
                    saveUserToLocal(authResponse.user)
                    Result.success(authResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception(errorBody ?: "Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun register(
        fullname: String,
        username: String,
        email: String,
        password: String
    ): Result<AuthResponse> {
        return try {
            val response = apiService.register(
                RegisterRequest(fullname, username, email, password, password)
            )
            if (response.isSuccessful) {
                val authResponse = response.body()
                if (authResponse != null && authResponse.user != null) {
                    // Save user to local database
                    saveUserToLocal(authResponse.user)
                    Result.success(authResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception(errorBody ?: "Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun googleSignIn(idToken: String): Result<AuthResponse> {
        return try {
            val response = apiService.googleLogin(GoogleLoginRequest(idToken))
            if (response.isSuccessful) {
                val authResponse = response.body()
                if (authResponse != null && authResponse.user != null) {
                    // Save user to local database
                    saveUserToLocal(authResponse.user)
                    Result.success(authResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception(errorBody ?: "Google sign-in failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun logout(): Result<ApiResponse> {
        return try {
            val response = apiService.logout("Bearer ${getStoredToken()}")
            if (response.isSuccessful) {
                // Clear local user data
                userDao.deleteAllUsers()
                Result.success(response.body() ?: ApiResponse(200, "Logged out", null))
            } else {
                Result.failure(Exception("Logout failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun verifyToken(): Result<UserResponse> {
        return try {
            val response = apiService.verifyToken("Bearer ${getStoredToken()}")
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
                Result.failure(Exception("Token verification failed"))
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