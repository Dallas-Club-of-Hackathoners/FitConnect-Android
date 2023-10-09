package com.stu.fitconnect.features.sportactivities.repositories

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivity
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivityFilters
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivitySummary
import kotlinx.coroutines.flow.Flow

class SportClubActivitiesRepositoryImpl: SportClubsActivitiesRepository {
    override fun getSportClubsActivitiesPagingList(searchBy: String, sportClubsActivityFilters: SportClubActivityFilters): Flow<PagingData<SportClubActivitySummary>> {
        TODO("Not yet implemented")
    }

    override fun getSportClubsActivitiesById(id: Int): SportClubActivity {
        TODO("Not yet implemented")
    }

}