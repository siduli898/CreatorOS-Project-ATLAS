package com.projectatlas.creatoros.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NeonCoral = Color(0xFFFF3366)
val ElectricViolet = Color(0xFF8A2BE2)
val GoldAccent = Color(0xFFFFD700)

@Composable
fun CreatorOSTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val scheme = if (darkTheme) darkColorScheme(primary = NeonCoral, secondary = ElectricViolet, tertiary = GoldAccent)
    else lightColorScheme(primary = NeonCoral, secondary = ElectricViolet, tertiary = GoldAccent)
    MaterialTheme(colorScheme = scheme, content = content)
}
