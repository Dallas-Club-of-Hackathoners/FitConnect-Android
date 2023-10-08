package com.stu.fitconnect.features.sportactivities.domain

import com.stu.fitconnect.features.sportsclubs.domain.AppLocation
import com.stu.fitconnect.features.sportsclubs.domain.Trainer

data class SportsClubActivity(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val location: AppLocation,
    val description: String,
    val cost: Int,
    val trainer: Trainer,
    val countVisitors: Int,
    val score: Double,
    val time: String,
    val isPossible: Boolean
)
