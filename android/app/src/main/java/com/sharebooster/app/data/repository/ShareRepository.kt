package com.sharebooster.app.data.repository

import com.sharebooster.app.data.local.dao.ShareLogDao
import com.sharebooster.app.data.local.dao.ShareSessionDao
import com.sharebooster.app.data.local.entity.ShareLogEntity
import com.sharebooster.app.data.local.entity.ShareSessionEntity
import com.sharebooster.app.data.model.*
import com.sharebooster.app.network.ApiService
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShareRepository @Inject constructor(
    private val apiService: ApiService,
    private val shareSessionDao: ShareSessionDao,
    private val shareLogDao: ShareLogDao
) {
    
    suspend fun startShareBoost(
        cookie: String,
        url: String,
        amount: Int,
        interval: Int
    ): Result<ApiResponse> {
        return try {
            val request = ShareBoostRequest(cookie, url, amount, interval)
            val response = apiService.startShareBoost("Bearer ${getStoredToken()}", request)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    // Create local session record
                    val sessionId = UUID.randomUUID().toString()
                    val session = ShareSessionEntity(
                        sessionId = sessionId,
                        userId = getCurrentUserId(),
                        url = url,
                        postId = null, // Will be updated when we get the response
                        amount = amount,
                        interval = interval,
                        currentCount = 0,
                        targetCount = amount,
                        status = "running",
                        startTime = System.currentTimeMillis(),
                        endTime = null
                    )
                    shareSessionDao.insertSession(session)
                    
                    Result.success(apiResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to start share boost"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun stopShareBoost(): Result<ApiResponse> {
        return try {
            val response = apiService.stopShareBoost("Bearer ${getStoredToken()}")
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    // Update local session status
                    val activeSession = shareSessionDao.getActiveSession(getCurrentUserId())
                    activeSession?.let { session ->
                        val updatedSession = session.copy(
                            status = "stopped",
                            endTime = System.currentTimeMillis()
                        )
                        shareSessionDao.updateSession(updatedSession)
                    }
                    
                    Result.success(apiResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to stop share boost"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getShareStatus(): Result<ShareStatusResponse> {
        return try {
            val response = apiService.getShareStatus("Bearer ${getStoredToken()}")
            if (response.isSuccessful) {
                val statusResponse = response.body()
                if (statusResponse != null) {
                    // Update local session if needed
                    statusResponse.count?.let { count ->
                        statusResponse.target?.let { target ->
                            val activeSession = shareSessionDao.getActiveSession(getCurrentUserId())
                            activeSession?.let { session ->
                                val updatedSession = session.copy(
                                    currentCount = count,
                                    targetCount = target,
                                    postId = statusResponse.postId
                                )
                                shareSessionDao.updateSession(updatedSession)
                            }
                        }
                    }
                    
                    Result.success(statusResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to get share status"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getShareLogs(): Result<ShareLogsResponse> {
        return try {
            val response = apiService.getShareLogs("Bearer ${getStoredToken()}")
            if (response.isSuccessful) {
                val logsResponse = response.body()
                if (logsResponse != null) {
                    // Save logs to local database
                    val sessionId = shareSessionDao.getActiveSession(getCurrentUserId())?.sessionId
                    sessionId?.let { id ->
                        val logEntities = logsResponse.logs.map { log ->
                            ShareLogEntity(
                                logId = UUID.randomUUID().toString(),
                                sessionId = id,
                                userId = getCurrentUserId(),
                                type = log.type,
                                message = log.message,
                                count = log.count,
                                target = log.target,
                                postId = log.postId,
                                timestamp = log.timestamp ?: System.currentTimeMillis().toString(),
                                success = log.success,
                                reason = log.reason
                            )
                        }
                        shareLogDao.insertLogs(logEntities)
                    }
                    
                    Result.success(logsResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to get share logs"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun clearShareLogs(): Result<ApiResponse> {
        return try {
            val response = apiService.clearShareLogs("Bearer ${getStoredToken()}")
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    // Clear local logs
                    shareLogDao.deleteLogsByUserId(getCurrentUserId())
                    
                    Result.success(apiResponse)
                } else {
                    Result.failure(Exception("Invalid response"))
                }
            } else {
                Result.failure(Exception("Failed to clear share logs"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getActiveSessionFlow(userId: String): Flow<ShareSessionEntity?> {
        return shareSessionDao.getActiveSessionFlow(userId)
    }
    
    fun getSessionsFlow(userId: String): Flow<List<ShareSessionEntity>> {
        return shareSessionDao.getSessionsByUserIdFlow(userId)
    }
    
    fun getLogsFlow(userId: String): Flow<List<ShareLogEntity>> {
        return shareLogDao.getLogsByUserIdFlow(userId)
    }
    
    suspend fun getActiveSession(userId: String): ShareSessionEntity? {
        return shareSessionDao.getActiveSession(userId)
    }
    
    suspend fun getSessions(userId: String): List<ShareSessionEntity> {
        return shareSessionDao.getSessionsByUserId(userId)
    }
    
    suspend fun getLogs(userId: String): List<ShareLogEntity> {
        return shareLogDao.getLogsByUserId(userId)
    }
    
    private fun getStoredToken(): String? {
        // This should be implemented using SharedPreferences or DataStore
        // For now, returning null - will be implemented in the ViewModel
        return null
    }
    
    private fun getCurrentUserId(): String {
        // This should be implemented using SharedPreferences or DataStore
        // For now, returning empty string - will be implemented in the ViewModel
        return ""
    }
}