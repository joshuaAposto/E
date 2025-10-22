package com.sharebooster.app.utils

object Constants {
    // API Configuration
    const val BASE_URL = "https://www.sharebooster.sbs/api/"
    
    // Google Sign-In
    const val GOOGLE_CLIENT_ID = "269620795252-otbo71hcr977ch2flulk728mr1rk3gif.apps.googleusercontent.com"
    
    // Shared Preferences
    const val PREFS_NAME = "sharebooster_prefs"
    const val KEY_AUTH_TOKEN = "auth_token"
    const val KEY_USER_ID = "user_id"
    const val KEY_USERNAME = "username"
    const val KEY_EMAIL = "email"
    const val KEY_FULLNAME = "fullname"
    const val KEY_IS_PREMIUM = "is_premium"
    const val KEY_PREMIUM_EXPIRATION = "premium_expiration"
    const val KEY_PFP_URL = "pfp_url"
    
    // Notification Channels
    const val SHARE_CHANNEL_ID = "share_boost_channel"
    const val SHARE_CHANNEL_NAME = "Share Boost"
    
    // Share Boost Limits
    const val FREE_SHARE_LIMIT = 500
    const val PREMIUM_SHARE_LIMIT_1_WEEK = 1000
    const val PREMIUM_SHARE_LIMIT_2_WEEKS = 2000
    const val PREMIUM_SHARE_LIMIT_1_YEAR = 4000
    
    // Animation Durations
    const val ANIMATION_DURATION_SHORT = 300L
    const val ANIMATION_DURATION_MEDIUM = 500L
    const val ANIMATION_DURATION_LONG = 1000L
    
    // UI Constants
    const val CARD_ELEVATION = 4f
    const val CARD_CORNER_RADIUS = 16f
    const val BUTTON_CORNER_RADIUS = 12f
    const val INPUT_CORNER_RADIUS = 12f
    
    // Network Timeouts
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
    
    // Pagination
    const val DEFAULT_PAGE_SIZE = 20
    
    // Validation
    const val MIN_PASSWORD_LENGTH = 6
    const val MIN_USERNAME_LENGTH = 3
    const val MAX_USERNAME_LENGTH = 20
    const val MAX_FULLNAME_LENGTH = 50
    
    // Share Boost
    const val MIN_INTERVAL_SECONDS = 1
    const val MAX_INTERVAL_SECONDS = 3600
    const val MIN_SHARE_AMOUNT = 1
    const val MAX_SHARE_AMOUNT = 10000
    
    // Error Messages
    const val ERROR_NETWORK = "Network error. Please check your connection."
    const val ERROR_INVALID_CREDENTIALS = "Invalid email or password."
    const val ERROR_EMAIL_EXISTS = "Email already exists."
    const val ERROR_USERNAME_EXISTS = "Username already exists."
    const val ERROR_PASSWORDS_DONT_MATCH = "Passwords don't match."
    const val ERROR_INVALID_EMAIL = "Please enter a valid email address."
    const val ERROR_PASSWORD_TOO_SHORT = "Password must be at least 6 characters."
    const val ERROR_REQUIRED_FIELD = "This field is required."
    const val ERROR_INVALID_URL = "Please enter a valid Facebook post URL."
    const val ERROR_BOOST_LIMIT = "You have reached your share limit."
    
    // Success Messages
    const val SUCCESS_LOGIN = "Login successful!"
    const val SUCCESS_REGISTER = "Registration successful!"
    const val SUCCESS_PROFILE_UPDATED = "Profile updated successfully!"
    const val SUCCESS_PASSWORD_CHANGED = "Password changed successfully!"
    const val SUCCESS_BOOST_STARTED = "Share boost started!"
    const val SUCCESS_BOOST_STOPPED = "Share boost stopped!"
    
    // Premium Plans
    const val PLAN_1_WEEK = "1 Week"
    const val PLAN_2_WEEKS = "2 Weeks"
    const val PLAN_1_YEAR = "1 Year"
    const val PLAN_PERMANENT = "Permanent"
}