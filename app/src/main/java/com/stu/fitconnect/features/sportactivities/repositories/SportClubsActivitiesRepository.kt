package com.stu.fitconnect.features.sportactivities.repositories

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivity
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivityFilters
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivitySummary

interface SportClubsActivitiesRepository {

    fun getSportClubsActivitiesPagingList(searchBy: String, sportClubsActivityFilters: SportClubActivityFilters): kotlinx.coroutines.flow.Flow<PagingData<SportClubActivitySummary>>
    fun getSportClubsActivitiesById(id: Int): SportClubActivity
}