package com.stu.fitconnect.features.sportactivities.domain.usecases

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivityFilters
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivitySummary
import com.stu.fitconnect.features.sportactivities.repositories.SportClubsActivitiesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSportClubsActivitiesListUseCase @Inject constructor(
    private val sportClubsActivitiesRepository: SportClubsActivitiesRepository
) {
    suspend fun getSportClubsActivitiesPagingList(searchBy: String, sportClubsActivityFilters: SportClubActivityFilters): Flow<PagingData<SportClubActivitySummary>> {
        return sportClubsActivitiesRepository.getSportClubsActivitiesPagingList(searchBy, sportClubsActivityFilters)
    }
}