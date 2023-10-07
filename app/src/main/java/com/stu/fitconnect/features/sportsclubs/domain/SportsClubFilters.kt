package com.stu.fitconnect.features.sportsclubs.domain

import com.google.gson.annotations.SerializedName

data class SportsClubsFilters(
    @SerializedName("isFavourite") val isFavourite: Boolean = false,
    val facilities: List<Facility> = emptyList(),
    val cost: List<Int> = emptyList(),
    val clubsCategory: List<ClubsCategory> = emptyList(),
    @SerializedName("sortType") val sortType: SortType
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


data class SportsClubsFilterTransaction(
    val isFavourite: Boolean = false,
    val facilities: List<String> = emptyList(),
    val cost: List<Int> = emptyList(),
    val clubsCategory: List<String> = emptyList()
)