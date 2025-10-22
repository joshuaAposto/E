package com.sharebooster.app.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.sharebooster.app.MainActivity
import com.sharebooster.app.R
import com.sharebooster.app.utils.Constants

class ShareService : Service() {
    
    companion object {
        const val ACTION_START_SHARE = "START_SHARE"
        const val ACTION_STOP_SHARE = "STOP_SHARE"
        const val EXTRA_URL = "URL"
        const val EXTRA_AMOUNT = "AMOUNT"
        const val EXTRA_INTERVAL = "INTERVAL"
        const val EXTRA_COOKIE = "COOKIE"
    }
    
    private var isRunning = false
    private var currentShares = 0
    private var targetShares = 0
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START_SHARE -> {
                val url = intent.getStringExtra(EXTRA_URL) ?: return START_NOT_STICKY
                val amount = intent.getIntExtra(EXTRA_AMOUNT, 0)
                val interval = intent.getIntExtra(EXTRA_INTERVAL, 5)
                val cookie = intent.getStringExtra(EXTRA_COOKIE) ?: return START_NOT_STICKY
                
                startShareBoost(url, amount, interval, cookie)
            }
            ACTION_STOP_SHARE -> {
                stopShareBoost()
            }
        }
        
        return START_NOT_STICKY
    }
    
    private fun startShareBoost(url: String, amount: Int, interval: Int, cookie: String) {
        isRunning = true
        currentShares = 0
        targetShares = amount
        
        startForeground(1, createNotification())
        
        // TODO: Implement actual share boost logic
        // This would involve making API calls to the backend
    }
    
    private fun stopShareBoost() {
        isRunning = false
        stopForeground(true)
        stopSelf()
    }
    
    private fun createNotification(): Notification {
        val channelId = Constants.SHARE_CHANNEL_ID
        val channelName = Constants.SHARE_CHANNEL_NAME
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Notifications for share boost activities"
                enableLights(false)
                enableVibration(false)
            }
            
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Share Boost Running")
            .setContentText("$currentShares / $targetShares shares completed")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setProgress(targetShares, currentShares, false)
            .build()
    }
    
    private fun updateNotification() {
        if (isRunning) {
            val notification = createNotification()
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notification)
        }
    }
}