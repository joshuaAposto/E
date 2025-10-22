package com.sharebooster.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.Configuration
import androidx.work.WorkManager
import com.sharebooster.app.data.local.ShareBoosterDatabase
import com.sharebooster.app.data.repository.AuthRepository
import com.sharebooster.app.data.repository.ShareRepository
import com.sharebooster.app.data.repository.UserRepository
import com.sharebooster.app.network.ApiService
import com.sharebooster.app.network.AuthInterceptor
import com.sharebooster.app.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ShareBoosterApplication : Application(), Configuration.Provider {

    // Database
    val database by lazy { ShareBoosterDatabase.getDatabase(this) }

    // Network
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = AuthInterceptor(this)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService by lazy { retrofit.create(ApiService::class.java) }

    // Repositories
    val authRepository by lazy { AuthRepository(apiService, database.userDao()) }
    val userRepository by lazy { UserRepository(apiService, database.userDao()) }
    val shareRepository by lazy { ShareRepository(apiService, database.shareSessionDao()) }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        WorkManager.initialize(this, workManagerConfiguration)
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Share Boost Channel
            val shareChannel = NotificationChannel(
                Constants.SHARE_CHANNEL_ID,
                "Share Boost",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Notifications for share boost activities"
                enableLights(false)
                enableVibration(false)
            }

            notificationManager.createNotificationChannel(shareChannel)
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
}