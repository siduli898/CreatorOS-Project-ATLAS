package com.projectatlas.creatoros.feature.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectatlas.creatoros.core.model.Post

@Composable
fun FeedScreen(posts: List<Post>, onUnlockPPV: (String) -> Unit, onSendTip: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (posts.isEmpty()) item { Text("No posts yet", style = MaterialTheme.typography.titleMedium) }
        items(posts) { post ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(post.creatorDisplayName, style = MaterialTheme.typography.titleMedium)
                    Text("@${post.creatorUsername}", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.height(8.dp))
                    Text(post.textContent)
                    Spacer(Modifier.height(12.dp))
                    if (post.isPPV && !post.canAccess) Button(onClick = { onUnlockPPV(post.id) }) { Text("Unlock PPV") }
                    else Text("Media available")
                    Spacer(Modifier.height(8.dp))
                    TextButton(onClick = { onSendTip(post.creatorId) }) { Text("Send Tip") }
                }
            }
        }
    }
}
