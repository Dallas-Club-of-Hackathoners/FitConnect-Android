package com.stu.fitconnect.features.sportactivities.domain

import java.util.Date

data class SportsClubActivitySummary(
    val id: Int,
    val date: Date,
    val name: String,
    val imagesRes: List<Int>,
    val score: Double,
    val cost: Int
)
