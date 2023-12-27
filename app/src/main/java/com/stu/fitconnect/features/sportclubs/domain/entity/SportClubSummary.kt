package com.stu.fitconnect.features.sportclubs.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class SportClubSummary(
    val id: Int,
    val name: String,
    val imagesUrls: List<String>,
    val location: AppLocation,
    val score: Double,
    val reviewsCount: Int,
    val category: String,
    val cost: Int,
    val isFavorite:Boolean
)

