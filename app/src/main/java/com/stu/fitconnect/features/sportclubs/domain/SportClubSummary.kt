package com.stu.fitconnect.features.sportclubs.domain

data class SportClubSummary(
    val id: Int,
    val name: String,
    val imagesRes: List<Int>,
    val location: AppLocation,
    val score: Double,
    val reviewsCount: Int,
    val cost: Int,
    val isFavorite:Boolean
)

