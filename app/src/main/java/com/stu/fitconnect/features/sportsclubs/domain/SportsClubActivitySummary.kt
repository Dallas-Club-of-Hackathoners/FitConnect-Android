package com.stu.fitconnect.features.sportsclubs.domain

data class SportsClubActivitySummary(
    val id: Int,
    val name: String,
    val imagesRes: List<Int>,
    val score: Double,
    val cost: Int,
)
