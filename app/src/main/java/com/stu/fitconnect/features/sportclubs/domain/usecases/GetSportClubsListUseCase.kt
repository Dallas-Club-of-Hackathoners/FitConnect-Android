package com.stu.fitconnect.features.sportclubs.domain.usecases

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import com.stu.fitconnect.network.spotclubs.repositories.SportClubsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSportClubsListUseCase @Inject constructor(
    private val sportClubsRepository: SportClubsRepository
) {
    suspend fun getSportsClubsPagingList(searchBy: String, sportClubsFilters: SportClubsFiltersData): Flow<PagingData<SportClubSummary>> {
        return sportClubsRepository.getSportClubsPagingList(searchBy, sportClubsFilters)
    }
}