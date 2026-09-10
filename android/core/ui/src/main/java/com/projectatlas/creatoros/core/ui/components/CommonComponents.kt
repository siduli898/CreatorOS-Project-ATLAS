package com.projectatlas.creatoros.core.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AdultComplianceBadge(performerRecordId: String?, modifier: Modifier = Modifier) {
    AssistChip(onClick = {}, label = { Text("18+ Verified") }, modifier = modifier)
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) { CircularProgressIndicator(modifier = modifier) }

@Composable
fun ErrorState(message: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onRetry, modifier = modifier) { Text(message) }
}
