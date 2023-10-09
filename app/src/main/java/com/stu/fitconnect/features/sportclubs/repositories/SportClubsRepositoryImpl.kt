package com.stu.fitconnect.features.sportclubs.repositories

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import kotlinx.coroutines.flow.Flow

class SportClubsRepositoryImpl: SportClubsRepository {

    override fun getSportClubsPagingList(searchBy: String, sportClubsFilters: SportClubsFiltersData): Flow<PagingData<SportClubSummary>> {
        TODO("Not yet implemented")
    }

    override fun getSportClubById(id: Int): SportClub {
        TODO("Not yet implemented")
    }
}