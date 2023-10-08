package com.stu.fitconnect.features.sportsclubs.presentation

import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters

sealed class SportsClubsListScreenState {

    object Initial : SportsClubsListScreenState()

    data class SportsClubsSearch(
        val searchText: String,
        val selectedFilters: SportsClubsFilters
    ) : SportsClubsListScreenState()
}