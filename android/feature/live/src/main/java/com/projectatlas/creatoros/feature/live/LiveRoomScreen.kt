package com.projectatlas.creatoros.feature.live

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectatlas.creatoros.core.model.LiveRoom

@Composable
fun LiveRoomScreen(room: LiveRoom, onSendTip: (Long) -> Unit, onLeaveRoom: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxWidth().weight(1f), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("LIVE STREAM\n${room.title}", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
        Surface(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("${room.viewerCount} viewers")
                Text("Tips: $${"%.2f".format(room.totalTipsCents / 100.0)}")
                Spacer(Modifier.height(12.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { onSendTip(500) }, modifier = Modifier.weight(1f)) { Text("Tip $5") }
                    Button(onClick = { onSendTip(2000) }, modifier = Modifier.weight(1f)) { Text("Tip $20") }
                }
                Spacer(Modifier.height(8.dp))
                OutlinedButton(onClick = onLeaveRoom, modifier = Modifier.fillMaxWidth()) { Text("Leave") }
            }
        }
    }
}
