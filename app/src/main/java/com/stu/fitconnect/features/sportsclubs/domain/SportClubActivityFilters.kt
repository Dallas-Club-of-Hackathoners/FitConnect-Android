package com.stu.fitconnect.features.sportsclubs.domain

data class SportClubActivityFilters(
    val isFavorite:Boolean=false,
    val clubsCategory: List<ClubsCategory> = emptyList(),
    val trainers: List<Trainer> = emptyList(),
    val time:List<String> = emptyList(),
)
