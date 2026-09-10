package com.projectatlas.creatoros.feature.marketplace

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectatlas.creatoros.core.model.ServiceListing

@Composable
fun MarketplaceScreen(listings: List<ServiceListing>, onOrderService: (String) -> Unit) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("Creator Services Marketplace", style = MaterialTheme.typography.headlineSmall) }
        items(listings) { listing ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(listing.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(6.dp))
                    Text(listing.description)
                    Spacer(Modifier.height(8.dp))
                    Text("$${"%.2f".format(listing.basePriceCents / 100.0)} • ${listing.deliveryDays} days")
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { onOrderService(listing.id) }) { Text("Order") }
                }
            }
        }
    }
}
