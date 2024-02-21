package com.stu.fitconnect.features.sportactivities.repositories

import com.stu.fitconnect.features.sportclubs.domain.entity.SortType

data class SportClubsActivityFilterTransaction(
    val isFavourite: Boolean = false,
    val coaches: List<String> = emptyList(),
    val time: List<String> = emptyList(),
    val activityCategory: List<String> = emptyList(),
    val sortTypes: SortType
)


