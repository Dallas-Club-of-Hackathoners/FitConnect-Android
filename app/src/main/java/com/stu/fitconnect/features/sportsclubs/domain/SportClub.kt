package com.stu.fitconnect.features.sportsclubs.domain

import com.stu.fitconnect.features.authentication.domain.User

data class SportClub(
    val id: Int,
    val name: String,
    val imagesRes: List<Int>,
    val location: AppLocation,
    val score: Double,
    val contacts: SportClubAdmin, //ссылка на акк админа клуба
    val description: String,
    val facilities: List<Facility>,
    val reviewsCount: Int,
    val reviews: List<Review>,
    val cost: Int,
    val trainers: List<Trainer>,
    val isFavorite: Boolean
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
    //занятие
)
data class SportClubAdmin(
    val id: Int,
    val name: String,
    val phone: String,
)