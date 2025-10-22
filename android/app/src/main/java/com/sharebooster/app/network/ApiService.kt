package com.sharebooster.app.network

import com.sharebooster.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    // Authentication
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
    
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>
    
    @POST("auth/google-login")
    suspend fun googleLogin(@Body request: GoogleLoginRequest): Response<AuthResponse>
    
    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") token: String): Response<ApiResponse>
    
    @GET("auth/verify")
    suspend fun verifyToken(@Header("Authorization") token: String): Response<UserResponse>
    
    // User Profile
    @GET("profile")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<UserResponse>
    
    @PUT("profile/update")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body request: UpdateProfileRequest
    ): Response<UserResponse>
    
    @POST("profile/change-password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body request: ChangePasswordRequest
    ): Response<ApiResponse>
    
    // Share Boost
    @POST("submit")
    suspend fun startShareBoost(
        @Header("Authorization") token: String,
        @Body request: ShareBoostRequest
    ): Response<ApiResponse>
    
    @POST("stop-share")
    suspend fun stopShareBoost(@Header("Authorization") token: String): Response<ApiResponse>
    
    @GET("share-status")
    suspend fun getShareStatus(@Header("Authorization") token: String): Response<ShareStatusResponse>
    
    @GET("share-logs")
    suspend fun getShareLogs(@Header("Authorization") token: String): Response<ShareLogsResponse>
    
    @DELETE("share-logs")
    suspend fun clearShareLogs(@Header("Authorization") token: String): Response<ApiResponse>
    
    // API Key Management
    @POST("apikey/generate")
    suspend fun generateApiKey(@Header("Authorization") token: String): Response<ApiKeyResponse>
    
    @GET("apikey/current")
    suspend fun getCurrentApiKey(@Header("Authorization") token: String): Response<ApiKeyResponse>
    
    @DELETE("apikey/revoke")
    suspend fun revokeApiKey(@Header("Authorization") token: String): Response<ApiResponse>
    
    // Cookie Management
    @POST("cookies/save")
    suspend fun saveCookie(
        @Header("Authorization") token: String,
        @Body request: SaveCookieRequest
    ): Response<ApiResponse>
    
    @GET("cookies/get")
    suspend fun getCookie(@Header("Authorization") token: String): Response<CookieResponse>
    
    @DELETE("cookies/delete")
    suspend fun deleteCookie(@Header("Authorization") token: String): Response<ApiResponse>
    
    // Premium
    @POST("premium/request")
    suspend fun requestPremium(
        @Header("Authorization") token: String,
        @Body request: PremiumRequest
    ): Response<ApiResponse>
    
    // Announcements
    @GET("announcements")
    suspend fun getAnnouncements(): Response<List<Announcement>>
    
    // Admin (if user is admin)
    @GET("admin/users")
    suspend fun getAdminUsers(@Header("Authorization") token: String): Response<List<User>>
    
    @PUT("admin/users/{userId}/premium")
    suspend fun updateUserPremium(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body request: UpdatePremiumRequest
    ): Response<ApiResponse>
    
    @PUT("admin/users/{userId}/status")
    suspend fun updateUserStatus(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body request: UpdateStatusRequest
    ): Response<ApiResponse>
    
    @DELETE("admin/users/{userId}")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Response<ApiResponse>
    
    @GET("admin/requests")
    suspend fun getAdminRequests(@Header("Authorization") token: String): Response<List<PremiumRequestData>>
    
    @PUT("admin/requests/{requestId}/approve")
    suspend fun approveRequest(
        @Header("Authorization") token: String,
        @Path("requestId") requestId: String
    ): Response<ApiResponse>
    
    @PUT("admin/requests/{requestId}/reject")
    suspend fun rejectRequest(
        @Header("Authorization") token: String,
        @Path("requestId") requestId: String
    ): Response<ApiResponse>
    
    @POST("admin/announcements")
    suspend fun createAnnouncement(
        @Header("Authorization") token: String,
        @Body request: CreateAnnouncementRequest
    ): Response<ApiResponse>
    
    @DELETE("admin/announcements/{id}")
    suspend fun deleteAnnouncement(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<ApiResponse>
}