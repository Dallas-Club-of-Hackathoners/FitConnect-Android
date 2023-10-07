package com.stu.fitconnect.features.sportsclubs.repositories

import com.stu.fitconnect.features.sportsclubs.domain.ClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters

interface SportsClubsRepository {

    fun getSportsClubList(sportsClubsFilters: SportsClubsFilters): List<ClubSummary>

}