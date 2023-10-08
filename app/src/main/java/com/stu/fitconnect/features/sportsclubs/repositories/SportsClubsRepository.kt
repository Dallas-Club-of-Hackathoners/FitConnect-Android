package com.stu.fitconnect.features.sportsclubs.repositories

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportsclubs.domain.SportClub
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters

interface SportsClubsRepository {

    fun getSportsClubsPagingList(searchBy: String, sportsClubsFilters: SportsClubsFilters): kotlinx.coroutines.flow.Flow<PagingData<SportClubSummary>>

    fun getSportClubById(id: Int): SportClub

}