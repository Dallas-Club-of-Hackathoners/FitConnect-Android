package com.stu.fitconnect.features.sportsclubs.domain

data class AppLocation(
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val city: String,
    val metro: String? = null
)