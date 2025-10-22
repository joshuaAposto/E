package com.sharebooster.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sharebooster.app.ui.screen.auth.LoginScreen
import com.sharebooster.app.ui.screen.auth.RegisterScreen
import com.sharebooster.app.ui.screen.dashboard.DashboardScreen
import com.sharebooster.app.ui.screen.profile.ProfileScreen
import com.sharebooster.app.ui.screen.shareboost.ShareBoostScreen
import com.sharebooster.app.ui.screen.settings.SettingsScreen
import com.sharebooster.app.ui.viewmodel.AuthViewModel
import com.sharebooster.app.ui.viewmodel.MainViewModel

@Composable
fun ShareBoosterNavigation(
    navController: NavHostController,
    authState: com.sharebooster.app.ui.viewmodel.AuthState,
    userState: com.sharebooster.app.ui.viewmodel.UserState,
    onLogin: (String, String) -> Unit,
    onRegister: (String, String, String, String) -> Unit,
    onGoogleSignIn: (String) -> Unit,
    onLogout: () -> Unit,
    onLoadUserProfile: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = if (authState.isAuthenticated) "dashboard" else "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginClick = { email, password ->
                    onLogin(email, password)
                },
                onRegisterClick = {
                    navController.navigate("register")
                },
                onGoogleSignInClick = { idToken ->
                    onGoogleSignIn(idToken)
                },
                isLoading = authState.isLoading,
                error = authState.error
            )
        }
        
        composable("register") {
            RegisterScreen(
                onRegisterClick = { fullname, username, email, password ->
                    onRegister(fullname, username, email, password)
                },
                onLoginClick = {
                    navController.navigate("login")
                },
                onGoogleSignInClick = { idToken ->
                    onGoogleSignIn(idToken)
                },
                isLoading = authState.isLoading,
                error = authState.error
            )
        }
        
        composable("dashboard") {
            DashboardScreen(
                onNavigateToShareBoost = {
                    navController.navigate("shareboost")
                },
                onNavigateToProfile = {
                    navController.navigate("profile")
                },
                onNavigateToSettings = {
                    navController.navigate("settings")
                },
                onLogout = {
                    onLogout()
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                user = userState.user,
                isLoading = userState.isLoading,
                error = userState.error
            )
        }
        
        composable("shareboost") {
            ShareBoostScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                user = userState.user,
                isLoading = userState.isLoading,
                error = userState.error
            )
        }
        
        composable("profile") {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onUpdateProfile = { fullname, username, email, removePfp ->
                    // Handle profile update
                },
                onChangePassword = { currentPassword, newPassword, confirmPassword ->
                    // Handle password change
                },
                onRequestPremium = { plan ->
                    // Handle premium request
                },
                user = userState.user,
                isLoading = userState.isLoading,
                error = userState.error
            )
        }
        
        composable("settings") {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onLogout = {
                    onLogout()
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                user = userState.user
            )
        }
    }
}