package com.projectatlas.creatoros.core.model

data class User(val id: String, val email: String, val username: String, val displayName: String, val role: UserRole, val is18PlusDeclared: Boolean, val isAgeVerified: Boolean, val avatarUrl: String? = null)
enum class UserRole { FAN, CREATOR, MODERATOR, ADMIN }
data class Post(val id: String, val creatorId: String, val creatorUsername: String, val creatorDisplayName: String, val textContent: String, val isPPV: Boolean, val ppvPriceCents: Long, val hasPurchased: Boolean, val canAccess: Boolean, val mediaAssets: List<MediaAsset>, val compliance2257Id: String?, val likesCount: Int, val commentsCount: Int, val createdAt: String)
data class MediaAsset(val id: String, val mediaType: MediaType, val thumbnailUrl: String, val durationSeconds: Int, val isLocked: Boolean, val playbackUrl: String?)
enum class MediaType { IMAGE, VIDEO, AUDIO }
data class SubscriptionTier(val id: String, val creatorId: String, val name: String, val description: String, val priceCents: Long, val perks: List<String>)
data class LiveRoom(val id: String, val creatorId: String, val title: String, val status: String, val viewerCount: Int, val totalTipsCents: Long, val streamSignalingEndpoint: String)
data class ServiceListing(val id: String, val creatorId: String, val title: String, val description: String, val category: String, val basePriceCents: Long, val deliveryDays: Int, val revisionLimit: Int)
data class LedgerStatement(val accountId: String, val currency: String, val currentBalanceCents: Long, val availableBalanceCents: Long, val entries: List<LedgerEntry>)
data class LedgerEntry(val id: String, val transactionId: String, val amountCents: Long, val runningBalanceAfterCents: Long, val description: String, val createdAt: String)
