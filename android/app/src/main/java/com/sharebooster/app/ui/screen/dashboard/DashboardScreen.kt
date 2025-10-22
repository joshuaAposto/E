package com.sharebooster.app.ui.screen.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sharebooster.app.R
import com.sharebooster.app.data.local.entity.UserEntity
import com.sharebooster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToShareBoost: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onLogout: () -> Unit,
    user: UserEntity?,
    isLoading: Boolean,
    error: String?
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Background, BackgroundSecondary)
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Welcome back,",
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextSecondary
                        )
                        Text(
                            text = user?.fullname ?: "User",
                            style = MaterialTheme.typography.headlineMedium,
                            color = TextMain,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Profile Picture
                        if (user?.pfpUrl != null) {
                            AsyncImage(
                                model = user.pfpUrl,
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(Primary, Secondary)
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = user?.username?.firstOrNull()?.uppercase() ?: "U",
                                    color = White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        
                        IconButton(
                            onClick = { showLogoutDialog = true }
                        ) {
                            Icon(
                                Icons.Default.Logout,
                                contentDescription = "Logout",
                                tint = TextMuted
                            )
                        }
                    }
                }
            }
            
            // Premium Status Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (user?.isPremium == true) PremiumGradientStart else CardBackground
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            if (user?.isPremium == true) Icons.Default.Star else Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = if (user?.isPremium == true) PremiumGold else TextMuted
                        )
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = if (user?.isPremium == true) "Premium Member" else "Free Account",
                                style = MaterialTheme.typography.titleMedium,
                                color = if (user?.isPremium == true) PremiumGold else TextMain,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (user?.isPremium == true) {
                                    "Unlimited shares • Priority support"
                                } else {
                                    "Upgrade for unlimited shares"
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (user?.isPremium == true) TextMain else TextMuted
                            )
                        }
                        
                        if (user?.isPremium != true) {
                            Button(
                                onClick = { /* TODO: Navigate to premium */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PremiumGold
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Upgrade",
                                    color = Black,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
            
            // Quick Actions
            item {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(getQuickActions()) { action ->
                        QuickActionCard(
                            title = action.title,
                            description = action.description,
                            icon = action.icon,
                            onClick = action.onClick
                        )
                    }
                }
            }
            
            // Features
            item {
                Text(
                    text = "Features",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(getFeatureCards()) { feature ->
                        FeatureCard(
                            title = feature.title,
                            description = feature.description,
                            icon = feature.icon,
                            onClick = feature.onClick
                        )
                    }
                }
            }
            
            // Stats
            item {
                Text(
                    text = "Statistics",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        title = "Total Shares",
                        value = "0",
                        icon = Icons.Default.Share,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Active Sessions",
                        value = "0",
                        icon = Icons.Default.PlayArrow,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
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
fun QuickActionCard(
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.width(160.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Primary, Secondary))
                    ),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = TextMain,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.width(200.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Primary.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    icon()
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted
            )
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Primary, Secondary))
                    ),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = TextMain,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted,
                textAlign = TextAlign.Center
            )
        }
    }
}

data class QuickAction(
    val title: String,
    val description: String,
    val icon: @Composable () -> Unit,
    val onClick: () -> Unit
)

data class Feature(
    val title: String,
    val description: String,
    val icon: @Composable () -> Unit,
    val onClick: () -> Unit
)

fun getQuickActions(): List<QuickAction> {
    return listOf(
        QuickAction(
            title = "Start Boost",
            description = "Boost your shares",
            icon = { Icon(Icons.Default.PlayArrow, contentDescription = null, tint = White) },
            onClick = { /* Navigate to share boost */ }
        ),
        QuickAction(
            title = "Profile",
            description = "Manage account",
            icon = { Icon(Icons.Default.Person, contentDescription = null, tint = White) },
            onClick = { /* Navigate to profile */ }
        )
    )
}

fun getFeatureCards(): List<Feature> {
    return listOf(
        Feature(
            title = "Share Analytics",
            description = "Track your share performance",
            icon = { Icon(Icons.Default.Analytics, contentDescription = null, tint = Primary) },
            onClick = { /* Navigate to analytics */ }
        ),
        Feature(
            title = "API Keys",
            description = "Manage your API access",
            icon = { Icon(Icons.Default.Key, contentDescription = null, tint = Primary) },
            onClick = { /* Navigate to API keys */ }
        ),
        Feature(
            title = "Settings",
            description = "App preferences",
            icon = { Icon(Icons.Default.Settings, contentDescription = null, tint = Primary) },
            onClick = { /* Navigate to settings */ }
        )
    )
}