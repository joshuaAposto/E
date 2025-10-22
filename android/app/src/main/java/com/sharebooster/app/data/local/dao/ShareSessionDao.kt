package com.sharebooster.app.data.local.dao

import androidx.room.*
import com.sharebooster.app.data.local.entity.ShareSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShareSessionDao {
    
    @Query("SELECT * FROM share_sessions WHERE sessionId = :sessionId")
    suspend fun getSessionById(sessionId: String): ShareSessionEntity?
    
    @Query("SELECT * FROM share_sessions WHERE userId = :userId ORDER BY startTime DESC")
    suspend fun getSessionsByUserId(userId: String): List<ShareSessionEntity>
    
    @Query("SELECT * FROM share_sessions WHERE userId = :userId ORDER BY startTime DESC")
    fun getSessionsByUserIdFlow(userId: String): Flow<List<ShareSessionEntity>>
    
    @Query("SELECT * FROM share_sessions WHERE userId = :userId AND status = 'running'")
    suspend fun getActiveSession(userId: String): ShareSessionEntity?
    
    @Query("SELECT * FROM share_sessions WHERE userId = :userId AND status = 'running'")
    fun getActiveSessionFlow(userId: String): Flow<ShareSessionEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: ShareSessionEntity)
    
    @Update
    suspend fun updateSession(session: ShareSessionEntity)
    
    @Delete
    suspend fun deleteSession(session: ShareSessionEntity)
    
    @Query("DELETE FROM share_sessions WHERE sessionId = :sessionId")
    suspend fun deleteSessionById(sessionId: String)
    
    @Query("DELETE FROM share_sessions WHERE userId = :userId")
    suspend fun deleteSessionsByUserId(userId: String)
    
    @Query("DELETE FROM share_sessions")
    suspend fun deleteAllSessions()
}