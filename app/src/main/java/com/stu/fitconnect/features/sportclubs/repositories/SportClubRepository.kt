package com.stu.fitconnect.features.sportclubs.repositories

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import kotlinx.coroutines.flow.Flow

interface SportClubRepository {

     suspend fun getSportClubById(id: Int): SportClub

     fun getSportClubsPagingList(searchBy: String, sportClubsFilters: SportClubsFiltersData?): Flow<PagingData<SportClubSummary>>


}