package com.stu.fitconnect.features.sportclubs.domain.entity

import com.stu.fitconnect.features.authentication.domain.User

data class SportClub(
    val id: Int,
    val name: String,
    val imagesRes: List<String>,
    val location: AppLocation,
    val score: Double,
    val contacts: SportClubAdmin, //ссылка на акк админа клуба ??
    val description: String,
    val facilities: List<String>,
    val reviewsCount: Int,
    val cost: String,
    val category: String,
    val isFavorite: Boolean
)

data class Review(
    val id: Int,
    val user: User,
    val text: String,
    val trainer: Trainer
)