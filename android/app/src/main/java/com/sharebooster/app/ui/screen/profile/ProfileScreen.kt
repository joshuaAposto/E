package com.sharebooster.app.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sharebooster.app.R
import com.sharebooster.app.data.local.entity.UserEntity
import com.sharebooster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onUpdateProfile: (String?, String?, String?, Boolean) -> Unit,
    onChangePassword: (String, String, String) -> Unit,
    onRequestPremium: (String) -> Unit,
    user: UserEntity?,
    isLoading: Boolean,
    error: String?
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var showPasswordDialog by remember { mutableStateOf(false) }
    var showPremiumDialog by remember { mutableStateOf(false) }

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
                    text = "Profile",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Profile Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile Picture
                    if (user?.pfpUrl != null) {
                        AsyncImage(
                            model = user.pfpUrl,
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
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
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = user?.fullname ?: "User",
                        style = MaterialTheme.typography.headlineMedium,
                        color = TextMain,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "@${user?.username ?: "username"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextSecondary
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Premium Badge
                    if (user?.isPremium == true) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = PremiumGradientStart),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = PremiumGold,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Premium Member",
                                    color = PremiumGold,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = { showEditDialog = true },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Primary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Edit")
                        }
                        
                        OutlinedButton(
                            onClick = { showPasswordDialog = true },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Secondary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Lock, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Password")
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Account Information
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
                        text = "Account Information",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextMain,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    InfoRow(
                        label = "Email",
                        value = user?.email ?: "N/A",
                        icon = Icons.Default.Email
                    )
                    
                    InfoRow(
                        label = "Username",
                        value = user?.username ?: "N/A",
                        icon = Icons.Default.Person
                    )
                    
                    InfoRow(
                        label = "Account Type",
                        value = if (user?.isPremium == true) "Premium" else "Free",
                        icon = if (user?.isPremium == true) Icons.Default.Star else Icons.Default.Person
                    )
                    
                    if (user?.isPremium == true && user.premiumExpiration != null) {
                        InfoRow(
                            label = "Premium Expires",
                            value = user.premiumExpiration,
                            icon = Icons.Default.CalendarToday
                        )
                    }
                    
                    InfoRow(
                        label = "Status",
                        value = user?.status?.replaceFirstChar { it.uppercase() } ?: "Active",
                        icon = if (user?.status == "active") Icons.Default.CheckCircle else Icons.Default.Warning
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Premium Section
            if (user?.isPremium != true) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PremiumGradientStart),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = PremiumGold
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Text(
                            text = "Upgrade to Premium",
                            style = MaterialTheme.typography.headlineSmall,
                            color = PremiumGold,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Text(
                            text = "Get unlimited shares and priority support",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextMain,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(
                            onClick = { showPremiumDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = PremiumGold),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Request Premium",
                                color = Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
    
    // Edit Profile Dialog
    if (showEditDialog) {
        EditProfileDialog(
            user = user,
            onDismiss = { showEditDialog = false },
            onSave = { fullname, username, email, removePfp ->
                onUpdateProfile(fullname, username, email, removePfp)
                showEditDialog = false
            }
        )
    }
    
    // Change Password Dialog
    if (showPasswordDialog) {
        ChangePasswordDialog(
            onDismiss = { showPasswordDialog = false },
            onChangePassword = { currentPassword, newPassword, confirmPassword ->
                onChangePassword(currentPassword, newPassword, confirmPassword)
                showPasswordDialog = false
            }
        )
    }
    
    // Premium Request Dialog
    if (showPremiumDialog) {
        PremiumRequestDialog(
            onDismiss = { showPremiumDialog = false },
            onRequestPremium = { plan ->
                onRequestPremium(plan)
                showPremiumDialog = false
            }
        )
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String,
    icon: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextMuted,
            modifier = Modifier.size(20.dp)
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = TextMain,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun EditProfileDialog(
    user: UserEntity?,
    onDismiss: () -> Unit,
    onSave: (String?, String?, String?, Boolean) -> Unit
) {
    var fullname by remember { mutableStateOf(user?.fullname ?: "") }
    var username by remember { mutableStateOf(user?.username ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var removePfp by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Edit Profile",
                color = TextMain,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = fullname,
                    onValueChange = { fullname = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSave(fullname, username, email, removePfp)
                }
            ) {
                Text("Save", color = Primary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = TextMuted)
            }
        },
        containerColor = CardBackground
    )
}

@Composable
fun ChangePasswordDialog(
    onDismiss: () -> Unit,
    onChangePassword: (String, String, String) -> Unit
) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Change Password",
                color = TextMain,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    label = { Text("Current Password") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("New Password") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onChangePassword(currentPassword, newPassword, confirmPassword)
                }
            ) {
                Text("Change", color = Primary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = TextMuted)
            }
        },
        containerColor = CardBackground
    )
}

@Composable
fun PremiumRequestDialog(
    onDismiss: () -> Unit,
    onRequestPremium: (String) -> Unit
) {
    var selectedPlan by remember { mutableStateOf("1 Week") }
    val plans = listOf("1 Week", "2 Weeks", "1 Year", "Permanent")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Request Premium",
                color = TextMain,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                Text(
                    text = "Select a premium plan:",
                    color = TextSecondary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                plans.forEach { plan ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedPlan == plan,
                            onClick = { selectedPlan = plan },
                            colors = RadioButtonDefaults.colors(selectedColor = Primary)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = plan,
                            color = TextMain
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onRequestPremium(selectedPlan)
                }
            ) {
                Text("Request", color = Primary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = TextMuted)
            }
        },
        containerColor = CardBackground
    )
}