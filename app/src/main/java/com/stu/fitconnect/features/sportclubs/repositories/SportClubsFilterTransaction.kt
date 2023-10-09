package com.stu.fitconnect.features.sportclubs.repositories

import com.stu.fitconnect.features.sportclubs.domain.SortType

data class SportClubsFilterTransaction(
    val isFavourite: Boolean = false,
    val facilities: List<String> = emptyList(),
    val cost: List<Int> = emptyList(),
    val clubsCategory: List<String> = emptyList(),
    val sortTypes: SortType
)