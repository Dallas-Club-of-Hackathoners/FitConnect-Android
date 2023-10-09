package com.stu.fitconnect.features.sportactivities.domain

import com.stu.fitconnect.features.sportclubs.domain.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.Trainer

data class SportClubActivity(
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
    val isPossible: Boolean,
    val isFavorite: Boolean
) //todo время че за парметр мб DATE() или что то подобное?
