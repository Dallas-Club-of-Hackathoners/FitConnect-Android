package com.stu.fitconnect.features.sportsclubs.repositories

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportsclubs.domain.SportClub
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import kotlinx.coroutines.flow.Flow

class SportsClubsRepositoryImpl: SportsClubsRepository {

    override fun getSportsClubsPagingList(searchBy: String, sportsClubsFilters: SportsClubsFilters): Flow<PagingData<SportClubSummary>> {
        TODO("Not yet implemented")
    }

    override fun getSportClubById(id: Int): SportClub {
        TODO("Not yet implemented")
    }
}