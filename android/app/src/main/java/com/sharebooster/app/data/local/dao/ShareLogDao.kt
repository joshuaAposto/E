package com.sharebooster.app.data.local.dao

import androidx.room.*
import com.sharebooster.app.data.local.entity.ShareLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShareLogDao {
    
    @Query("SELECT * FROM share_logs WHERE logId = :logId")
    suspend fun getLogById(logId: String): ShareLogEntity?
    
    @Query("SELECT * FROM share_logs WHERE sessionId = :sessionId ORDER BY timestamp DESC")
    suspend fun getLogsBySessionId(sessionId: String): List<ShareLogEntity>
    
    @Query("SELECT * FROM share_logs WHERE sessionId = :sessionId ORDER BY timestamp DESC")
    fun getLogsBySessionIdFlow(sessionId: String): Flow<List<ShareLogEntity>>
    
    @Query("SELECT * FROM share_logs WHERE userId = :userId ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getLogsByUserId(userId: String, limit: Int = 100): List<ShareLogEntity>
    
    @Query("SELECT * FROM share_logs WHERE userId = :userId ORDER BY timestamp DESC LIMIT :limit")
    fun getLogsByUserIdFlow(userId: String, limit: Int = 100): Flow<List<ShareLogEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: ShareLogEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogs(logs: List<ShareLogEntity>)
    
    @Update
    suspend fun updateLog(log: ShareLogEntity)
    
    @Delete
    suspend fun deleteLog(log: ShareLogEntity)
    
    @Query("DELETE FROM share_logs WHERE logId = :logId")
    suspend fun deleteLogById(logId: String)
    
    @Query("DELETE FROM share_logs WHERE sessionId = :sessionId")
    suspend fun deleteLogsBySessionId(sessionId: String)
    
    @Query("DELETE FROM share_logs WHERE userId = :userId")
    suspend fun deleteLogsByUserId(userId: String)
    
    @Query("DELETE FROM share_logs")
    suspend fun deleteAllLogs()
}