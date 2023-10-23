package com.stu.fitconnect.features.sportclubs.domain.entity

data class Amenity(
    var id: Int,
    var name: String,
    var iconRes: String,
)

data class AmenityWithAvailable(
    var id: Int,
    var name: String,
    var iconRes: String,
    var available: Boolean
)