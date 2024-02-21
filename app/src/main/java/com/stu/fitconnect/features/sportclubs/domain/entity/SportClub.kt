package com.stu.fitconnect.features.sportclubs.domain.entity

import com.stu.fitconnect.features.authentication.domain.User

data class SportClub(
    val id: Int,
    val name: String,
    val imagesUrls: List<String>,
    val location: AppLocation,
    val score: Double,
    val contacts: SportClubAdmin, //ссылка на акк админа клуба ??
    val description: String,
    val amenities: List<AmenityWithAvailable>,
    val reviewsCount: Int,
    val cost: Int,
    val category: String,
    val isFavorite: Boolean
)

data class Review(
    val id: Int,
    val user: User,
    val text: String,
    val trainer: Trainer
)