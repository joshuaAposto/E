package com.sharebooster.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.sharebooster.app.ui.navigation.ShareBoosterNavigation
import com.sharebooster.app.ui.theme.ShareBoosterTheme
import com.sharebooster.app.ui.viewmodel.AuthViewModel
import com.sharebooster.app.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory((application as ShareBoosterApplication).authRepository)
    }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as ShareBoosterApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge()
        
        setContent {
            ShareBoosterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShareBoosterApp(
                        authViewModel = authViewModel,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ShareBoosterApp(
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()
    val userState by mainViewModel.userState.collectAsState()

    LaunchedEffect(authState.isAuthenticated) {
        if (authState.isAuthenticated && userState.user == null) {
            mainViewModel.loadUserProfile()
        }
    }

    ShareBoosterNavigation(
        navController = navController,
        authState = authState,
        userState = userState,
        onLogin = { email, password ->
            authViewModel.login(email, password)
        },
        onRegister = { fullname, username, email, password ->
            authViewModel.register(fullname, username, email, password)
        },
        onGoogleSignIn = { idToken ->
            authViewModel.googleSignIn(idToken)
        },
        onLogout = {
            authViewModel.logout()
        },
        onLoadUserProfile = {
            mainViewModel.loadUserProfile()
        }
    )
}