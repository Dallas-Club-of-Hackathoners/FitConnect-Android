package com.stu.fitconnect.features.sportsclubs.repositories

import com.stu.fitconnect.features.sportsclubs.domain.SportClub
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters

class SportsClubsRepositoryImpl: SportsClubsRepository {

    override fun getSportsClubsList(searchBy: String, sportsClubsFilters: SportsClubsFilters): List<SportClubSummary> {
        TODO("Not yet implemented")
    }

    override fun getSportClubById(id: Int): SportClub {
        TODO("Not yet implemented")
    }
}