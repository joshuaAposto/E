package com.sharebooster.app.network

import android.content.Context
import com.sharebooster.app.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        
        // Get token from SharedPreferences
        val prefs = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        val token = prefs.getString(Constants.KEY_AUTH_TOKEN, null)
        
        // Add Authorization header if token exists
        val newRequest = if (token != null) {
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }
        
        return chain.proceed(newRequest)
    }
}