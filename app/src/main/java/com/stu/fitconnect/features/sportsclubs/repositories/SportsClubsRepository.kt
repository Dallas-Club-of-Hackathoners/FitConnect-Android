package com.stu.fitconnect.features.sportsclubs.repositories

import com.stu.fitconnect.features.sportsclubs.domain.SportClub
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters

interface SportsClubsRepository {

    fun getSportsClubsList(searchBy: String, sportsClubsFilters: SportsClubsFilters): List<SportClubSummary>

    fun getSportClubById(id: Int): SportClub

}