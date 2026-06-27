package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val CinematicDarkColorScheme = darkColorScheme(
  primary = GoldAccent,
  onPrimary = DarkSpaceBg,
  secondary = CyanPlayer,
  onSecondary = DarkSpaceBg,
  tertiary = GlassGreen,
  background = DarkSpaceBg,
  surface = DarkCardBg,
  onBackground = TextWhite,
  onSurface = TextWhite,
  error = WarningRed,
  outline = DarkCardBorder
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Force cinematic dark theme for media portal style
  dynamicColor: Boolean = false, // Keep custom gold & cyan branding consistent
  content: @Composable () -> Unit,
) {
  val colorScheme = CinematicDarkColorScheme

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
