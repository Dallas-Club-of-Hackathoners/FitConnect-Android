package com.stu.fitconnect.features.sportclubs.repositories

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData

interface SportClubsRepository {

    fun getSportClubsPagingList(searchBy: String, sportClubsFilters: SportClubsFiltersData): kotlinx.coroutines.flow.Flow<PagingData<SportClubSummary>>


}