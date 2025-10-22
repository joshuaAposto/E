package com.sharebooster.app.data.local.dao

import androidx.room.*
import com.sharebooster.app.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    
    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUserByIdFlow(userId: String): Flow<UserEntity?>
    
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): UserEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Delete
    suspend fun deleteUser(user: UserEntity)
    
    @Query("DELETE FROM users WHERE userId = :userId")
    suspend fun deleteUserById(userId: String)
    
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
    
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>
    
    @Query("SELECT * FROM users")
    fun getAllUsersFlow(): Flow<List<UserEntity>>
}