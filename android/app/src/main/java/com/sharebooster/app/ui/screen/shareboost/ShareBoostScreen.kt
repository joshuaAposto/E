package com.sharebooster.app.ui.screen.shareboost

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharebooster.app.R
import com.sharebooster.app.data.local.entity.UserEntity
import com.sharebooster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareBoostScreen(
    onNavigateBack: () -> Unit,
    user: UserEntity?,
    isLoading: Boolean,
    error: String?
) {
    var facebookUrl by remember { mutableStateOf("") }
    var shareAmount by remember { mutableStateOf("") }
    var interval by remember { mutableStateOf("") }
    var cookie by remember { mutableStateOf("") }
    var isBoostRunning by remember { mutableStateOf(false) }
    var currentShares by remember { mutableStateOf(0) }
    var targetShares by remember { mutableStateOf(0) }

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
                    text = "Share Boost",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Boost Status Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (isBoostRunning) Success else CardBackground
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        if (isBoostRunning) Icons.Default.PlayArrow else Icons.Default.Pause,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = if (isBoostRunning) White else TextMuted
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Text(
                        text = if (isBoostRunning) "Boost Running" else "Boost Stopped",
                        style = MaterialTheme.typography.titleLarge,
                        color = if (isBoostRunning) White else TextMain,
                        fontWeight = FontWeight.Bold
                    )
                    
                    if (isBoostRunning) {
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "$currentShares / $targetShares shares completed",
                            style = MaterialTheme.typography.bodyLarge,
                            color = White
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        LinearProgressIndicator(
                            progress = if (targetShares > 0) currentShares.toFloat() / targetShares.toFloat() else 0f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = White,
                            trackColor = White.copy(alpha = 0.3f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Configuration Form
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
                        text = "Boost Configuration",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextMain,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Facebook URL
                    OutlinedTextField(
                        value = facebookUrl,
                        onValueChange = { facebookUrl = it },
                        label = { Text("Facebook Post URL") },
                        placeholder = { Text("https://facebook.com/...") },
                        leadingIcon = {
                            Icon(Icons.Default.Link, contentDescription = null)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = BorderLight,
                            focusedLabelColor = Primary,
                            unfocusedLabelColor = TextMuted,
                            focusedTextColor = TextMain,
                            unfocusedTextColor = TextMain
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Share Amount
                    OutlinedTextField(
                        value = shareAmount,
                        onValueChange = { shareAmount = it },
                        label = { Text("Share Amount") },
                        placeholder = { Text("100") },
                        leadingIcon = {
                            Icon(Icons.Default.Numbers, contentDescription = null)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = BorderLight,
                            focusedLabelColor = Primary,
                            unfocusedLabelColor = TextMuted,
                            focusedTextColor = TextMain,
                            unfocusedTextColor = TextMain
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Interval
                    OutlinedTextField(
                        value = interval,
                        onValueChange = { interval = it },
                        label = { Text("Interval (seconds)") },
                        placeholder = { Text("5") },
                        leadingIcon = {
                            Icon(Icons.Default.Timer, contentDescription = null)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = BorderLight,
                            focusedLabelColor = Primary,
                            unfocusedLabelColor = TextMuted,
                            focusedTextColor = TextMain,
                            unfocusedTextColor = TextMain
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Cookie
                    OutlinedTextField(
                        value = cookie,
                        onValueChange = { cookie = it },
                        label = { Text("Facebook Cookie") },
                        placeholder = { Text("Paste your Facebook cookie here") },
                        leadingIcon = {
                            Icon(Icons.Default.Security, contentDescription = null)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = BorderLight,
                            focusedLabelColor = Primary,
                            unfocusedLabelColor = TextMuted,
                            focusedTextColor = TextMain,
                            unfocusedTextColor = TextMain
                        ),
                        maxLines = 4
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (!isBoostRunning) {
                            Button(
                                onClick = {
                                    if (facebookUrl.isNotBlank() && shareAmount.isNotBlank() && 
                                        interval.isNotBlank() && cookie.isNotBlank()) {
                                        isBoostRunning = true
                                        targetShares = shareAmount.toIntOrNull() ?: 0
                                        currentShares = 0
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = Success),
                                shape = RoundedCornerShape(12.dp),
                                enabled = facebookUrl.isNotBlank() && shareAmount.isNotBlank() && 
                                        interval.isNotBlank() && cookie.isNotBlank()
                            ) {
                                Icon(Icons.Default.PlayArrow, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Start Boost")
                            }
                        } else {
                            Button(
                                onClick = {
                                    isBoostRunning = false
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = Error),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.Stop, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Stop Boost")
                            }
                        }
                        
                        OutlinedButton(
                            onClick = { /* TODO: Show help */ },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Primary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Help, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Help")
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Share Limits Info
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
                        text = "Share Limits",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextMain,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Free Account:",
                            color = TextSecondary
                        )
                        Text(
                            text = "500 shares/session",
                            color = TextMain,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    
                    if (user?.isPremium == true) {
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Premium Account:",
                                color = TextSecondary
                            )
                            Text(
                                text = "Unlimited shares",
                                color = PremiumGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}