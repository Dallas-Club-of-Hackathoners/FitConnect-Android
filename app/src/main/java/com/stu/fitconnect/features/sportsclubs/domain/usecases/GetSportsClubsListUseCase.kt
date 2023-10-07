package com.stu.fitconnect.features.sportsclubs.domain.usecases

import com.stu.fitconnect.features.sportsclubs.domain.ClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import com.stu.fitconnect.features.sportsclubs.repositories.SportsClubsRepository

class GetSportsClubsListUseCase(
    private val sportsClubsRepository: SportsClubsRepository
) {
    operator suspend fun invoke(sportsClubsFilters: SportsClubsFilters): List<ClubSummary> {
        return sportsClubsRepository.getSportsClubList(sportsClubsFilters)
    }
}