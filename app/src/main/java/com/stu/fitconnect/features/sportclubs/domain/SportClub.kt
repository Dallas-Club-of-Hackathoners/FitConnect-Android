package com.stu.fitconnect.features.sportclubs.domain

import com.stu.fitconnect.features.authentication.domain.User

data class SportClub(
    val id: Int,
    val name: String,
    val imagesRes: List<String>,
    val location: AppLocation,
    val score: Double,
    val contacts: SportClubAdmin, //ссылка на акк админа клуба ??
    val description: String,
    val facilities: List<Facility>,
    val reviewsCount: Int,
    val cost: Int,
    val isFavorite: Boolean
)

data class SportClubAdmin(
    val id: Int,
    val name: String,
    val phone: String,
)

data class Facility(
    var id: String,
    var name: String,
    var iconRes: Int,
)

data class Trainer(
    val id: Int,
    val name: String,
    val score: Double,
)
data class Review(
    val id: Int,
    val user: User,
    val text: String,
    val trainer: Trainer
)