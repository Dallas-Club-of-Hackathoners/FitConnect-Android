package com.stu.fitconnect.features.sportclubs.domain

data class SportClubSummary(
    val id: Int,
    val name: String,
    val imagesRes: List<String>,
    val location: AppLocation,
    val score: Double,
    val reviewsCount: Int,
    val category: String,
    val cost: String,
    val isFavorite:Boolean
)

