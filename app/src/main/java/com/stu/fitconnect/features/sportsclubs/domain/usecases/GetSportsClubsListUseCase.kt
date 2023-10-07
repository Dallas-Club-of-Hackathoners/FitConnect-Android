package com.stu.fitconnect.features.sportsclubs.domain.usecases

import com.stu.fitconnect.features.sportsclubs.domain.ClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import com.stu.fitconnect.features.sportsclubs.repositories.SportsClubsRepository
import javax.inject.Inject

class GetSportsClubsListUseCase @Inject constructor(
    private val sportsClubsRepository: SportsClubsRepository
) {
     operator suspend fun invoke(searchBy: String, sportsClubsFilters: SportsClubsFilters): List<ClubSummary> {
        return sportsClubsRepository.getSportsClubList(searchBy,sportsClubsFilters)
    }
}