package com.sharebooster.app.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sharebooster.app.R
import com.sharebooster.app.data.local.entity.UserEntity
import com.sharebooster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit,
    user: UserEntity?
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Background, BackgroundSecondary)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = TextMain
                    )
                }
                
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // App Settings
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "App Settings",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextMain,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    SettingsItem(
                        icon = Icons.Default.Notifications,
                        title = "Notifications",
                        subtitle = "Push notifications for boost updates",
                        trailing = {
                            Switch(
                                checked = notificationsEnabled,
                                onCheckedChange = { notificationsEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = White,
                                    checkedTrackColor = Primary,
                                    uncheckedThumbColor = TextMuted,
                                    uncheckedTrackColor = BorderLight
                                )
                            )
                        }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.DarkMode,
                        title = "Dark Mode",
                        subtitle = "Use dark theme",
                        trailing = {
                            Switch(
                                checked = darkModeEnabled,
                                onCheckedChange = { darkModeEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = White,
                                    checkedTrackColor = Primary,
                                    uncheckedThumbColor = TextMuted,
                                    uncheckedTrackColor = BorderLight
                                )
                            )
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Account Settings
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Account",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextMain,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    SettingsItem(
                        icon = Icons.Default.Person,
                        title = "Profile",
                        subtitle = "Manage your profile information",
                        onClick = { /* Navigate to profile */ }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Key,
                        title = "API Keys",
                        subtitle = "Manage your API access keys",
                        onClick = { /* Navigate to API keys */ }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Security,
                        title = "Privacy",
                        subtitle = "Privacy and security settings",
                        onClick = { /* Navigate to privacy */ }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Support
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Support",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextMain,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    SettingsItem(
                        icon = Icons.Default.Help,
                        title = "Help Center",
                        subtitle = "Get help and support",
                        onClick = { /* Navigate to help */ }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Email,
                        title = "Contact Support",
                        subtitle = "Send us a message",
                        onClick = { /* Navigate to contact */ }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Info,
                        title = "About",
                        subtitle = "App version and information",
                        onClick = { /* Navigate to about */ }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Danger Zone
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Error.copy(alpha = 0.1f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Danger Zone",
                        style = MaterialTheme.typography.titleLarge,
                        color = Error,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    SettingsItem(
                        icon = Icons.Default.Logout,
                        title = "Logout",
                        subtitle = "Sign out of your account",
                        textColor = Error,
                        onClick = { showLogoutDialog = true }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
    
    // Logout Dialog
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = {
                Text(
                    text = "Logout",
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to logout?",
                    color = TextSecondary
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    }
                ) {
                    Text(
                        text = "Logout",
                        color = Error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLogoutDialog = false }
                ) {
                    Text(
                        text = "Cancel",
                        color = TextMuted
                    )
                }
            },
            containerColor = CardBackground
        )
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    textColor: androidx.compose.ui.graphics.Color = TextMain,
    onClick: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    Card(
        onClick = { onClick?.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted
                )
            }
            
            if (trailing != null) {
                trailing()
            } else if (onClick != null) {
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = TextMuted,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}