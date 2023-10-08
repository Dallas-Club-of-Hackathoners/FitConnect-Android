package com.stu.fitconnect.features.sportsclubs.repositories

import com.stu.fitconnect.features.sportsclubs.domain.SortType

data class SportsClubsFilterTransaction(
    val isFavourite: Boolean = false,
    val facilities: List<String> = emptyList(),
    val cost: List<Int> = emptyList(),
    val clubsCategory: List<String> = emptyList(),
    val sortTypes: SortType

)