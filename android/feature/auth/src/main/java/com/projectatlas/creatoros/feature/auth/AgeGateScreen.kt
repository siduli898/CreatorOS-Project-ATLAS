package com.projectatlas.creatoros.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectatlas.creatoros.core.ui.theme.NeonCoral

@Composable
fun AgeGateScreen(onConfirmed18Plus: () -> Unit, onExitApp: () -> Unit) {
    var confirmed by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(color = NeonCoral.copy(alpha = .15f), shape = RoundedCornerShape(16.dp)) {
            Text("18+", color = NeonCoral, fontSize = 48.sp, fontWeight = FontWeight.Black,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp))
        }
        Spacer(Modifier.height(16.dp))
        Text("Adults Only Content Platform", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))
        Text("CreatorOS is restricted to adults. You must be 18 or older, or the age of majority in your jurisdiction, to continue.", textAlign = TextAlign.Center)
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Checkbox(checked = confirmed, onCheckedChange = { confirmed = it })
            Text("I am at least 18 years old and agree to age verification and the terms of service.", style = MaterialTheme.typography.bodySmall)
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = onConfirmed18Plus, enabled = confirmed, modifier = Modifier.fillMaxWidth().height(52.dp)) { Text("I Am 18 or Older") }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = onExitApp, modifier = Modifier.fillMaxWidth().height(52.dp)) { Text("Exit") }
    }
}
