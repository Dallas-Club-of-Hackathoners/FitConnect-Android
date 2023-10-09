package com.stu.fitconnect.features.sportactivities.domain

import java.util.Date

data class SportClubActivitySummary(
    val id: Int,
    val date: String, //было Date
    val name: String,
    val imagesRes: List<Int>,
    val score: Double,
    val cost: Int
)
