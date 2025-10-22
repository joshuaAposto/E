package com.sharebooster.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = White,
    primaryContainer = PrimaryDark,
    onPrimaryContainer = White,
    
    secondary = Secondary,
    onSecondary = White,
    secondaryContainer = SecondaryLight,
    onSecondaryContainer = Black,
    
    tertiary = Accent,
    onTertiary = White,
    tertiaryContainer = AccentLight,
    onTertiaryContainer = Black,
    
    error = Error,
    onError = White,
    errorContainer = ErrorLight,
    onErrorContainer = Black,
    
    background = Background,
    onBackground = TextMain,
    surface = CardBackground,
    onSurface = TextMain,
    surfaceVariant = BackgroundSecondary,
    onSurfaceVariant = TextSecondary,
    
    outline = BorderLight,
    outlineVariant = BorderAccent,
    
    scrim = OverlayDark,
    surfaceTint = Primary,
    inverseSurface = TextMain,
    inverseOnSurface = Background,
    inversePrimary = PrimaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = White,
    primaryContainer = PrimaryLight,
    onPrimaryContainer = PrimaryDark,
    
    secondary = Secondary,
    onSecondary = White,
    secondaryContainer = SecondaryLight,
    onSecondaryContainer = Black,
    
    tertiary = Accent,
    onTertiary = White,
    tertiaryContainer = AccentLight,
    onTertiaryContainer = Black,
    
    error = Error,
    onError = White,
    errorContainer = ErrorLight,
    onErrorContainer = Black,
    
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = BackgroundSecondary,
    onSurfaceVariant = TextSecondary,
    
    outline = BorderLight,
    outlineVariant = BorderAccent,
    
    scrim = OverlayLight,
    surfaceTint = Primary,
    inverseSurface = Black,
    inverseOnSurface = White,
    inversePrimary = PrimaryLight
)

@Composable
fun ShareBoosterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}