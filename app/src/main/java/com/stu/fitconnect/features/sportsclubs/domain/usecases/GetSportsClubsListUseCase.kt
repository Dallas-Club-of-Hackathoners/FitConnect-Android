package com.stu.fitconnect.features.sportsclubs.domain.usecases

import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import com.stu.fitconnect.features.sportsclubs.repositories.SportsClubsRepository
import javax.inject.Inject

class GetSportsClubsListUseCase @Inject constructor(
    private val sportsClubsRepository: SportsClubsRepository
) {
    suspend fun getSportsClubsList(searchBy: String, sportsClubsFilters: SportsClubsFilters): List<SportClubSummary> {
        return sportsClubsRepository.getSportsClubsList(searchBy, sportsClubsFilters)
    }
}