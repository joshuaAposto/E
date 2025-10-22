package com.sharebooster.app.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.sharebooster.app.data.local.dao.ShareLogDao
import com.sharebooster.app.data.local.dao.ShareSessionDao
import com.sharebooster.app.data.local.dao.UserDao
import com.sharebooster.app.data.local.entity.ShareLogEntity
import com.sharebooster.app.data.local.entity.ShareSessionEntity
import com.sharebooster.app.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ShareSessionEntity::class,
        ShareLogEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ShareBoosterDatabase : RoomDatabase() {
    
    abstract fun userDao(): UserDao
    abstract fun shareSessionDao(): ShareSessionDao
    abstract fun shareLogDao(): ShareLogDao
    
    companion object {
        @Volatile
        private var INSTANCE: ShareBoosterDatabase? = null
        
        fun getDatabase(context: Context): ShareBoosterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShareBoosterDatabase::class.java,
                    "sharebooster_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}