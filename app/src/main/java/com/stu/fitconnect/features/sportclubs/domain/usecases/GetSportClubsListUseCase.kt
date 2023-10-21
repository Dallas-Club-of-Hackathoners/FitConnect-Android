package com.stu.fitconnect.features.sportclubs.domain.usecases

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.features.sportclubs.repositories.SportClubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSportClubsListUseCase @Inject constructor(
    private val sportClubRepository: SportClubRepository
) {
    suspend fun getSportsClubsPagingList(searchBy: String, sportClubsFilters: SportClubsFiltersData): Flow<PagingData<SportClubSummary>> {
        return sportClubRepository.getSportClubsPagingList(searchBy, sportClubsFilters)
    }
}