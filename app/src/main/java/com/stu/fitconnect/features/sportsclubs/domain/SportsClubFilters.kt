package com.stu.fitconnect.features.sportsclubs.domain


data class SportsClubsFilters(
    val isFavourite: Boolean = false,
    val facilities: List<Facility> = emptyList(),
    val cost: List<Int> = emptyList(),
    val clubsCategory: List<ClubsCategory> = emptyList(),
    val sortTypes: List<SortType> = emptyList()
)

data class Facility(
    val id: String,
    val name: String,
    val iconRes: Int,
    val isSelected: Boolean = false
)

data class ClubsCategory(
    val id: String,
    val name: String,
    val isSelected: Boolean = false
)


data class SortType(
    val id: String,
    val name: String,
)

