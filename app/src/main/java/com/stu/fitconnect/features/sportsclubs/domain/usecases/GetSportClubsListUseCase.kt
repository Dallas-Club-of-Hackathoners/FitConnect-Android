package com.stu.fitconnect.features.sportsclubs.domain.usecases

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFiltersData
import com.stu.fitconnect.features.sportsclubs.repositories.SportClubsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSportClubsListUseCase @Inject constructor(
    private val sportClubsRepository: SportClubsRepository
) {
    suspend fun getSportsClubsPagingList(searchBy: String, sportsClubsFilters: SportsClubsFiltersData): Flow<PagingData<SportClubSummary>> {
        return sportClubsRepository.getSportsClubsPagingList(searchBy, sportsClubsFilters)
    }
}