package com.projectatlas.creatoros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.projectatlas.creatoros.core.model.*
import com.projectatlas.creatoros.core.ui.theme.CreatorOSTheme
import com.projectatlas.creatoros.feature.auth.AgeGateScreen
import com.projectatlas.creatoros.feature.feed.FeedScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreatorOSTheme {
                var verified by remember { mutableStateOf(false) }
                Surface(Modifier.fillMaxSize()) {
                    if (!verified) AgeGateScreen({ verified = true }, { finish() })
                    else FeedScreen(emptyList(), {}, {})
                }
            }
        }
    }
}
