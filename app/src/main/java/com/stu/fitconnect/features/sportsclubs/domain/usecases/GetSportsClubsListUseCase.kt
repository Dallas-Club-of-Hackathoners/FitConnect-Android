package com.stu.fitconnect.features.sportsclubs.domain.usecases

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFiltersData
import com.stu.fitconnect.features.sportsclubs.repositories.SportsClubsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSportsClubsListUseCase @Inject constructor(
    private val sportsClubsRepository: SportsClubsRepository
) {
    suspend fun getSportsClubsPagingList(searchBy: String, sportsClubsFilters: SportsClubsFiltersData): Flow<PagingData<SportClubSummary>> {
        return sportsClubsRepository.getSportsClubsPagingList(searchBy, sportsClubsFilters)
    }
}