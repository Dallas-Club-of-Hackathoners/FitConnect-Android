package com.stu.fitconnect.network.sportclub.source

import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.network.sportclub.api.response.SportClubResponse

interface SportClubSource {

    suspend fun getSportClubFullInfo(id: Int, userId: String): SportClubResponse

    suspend fun getSummarySportClubsPagingList(filters: SportClubsFiltersData?, searchBy: String, pageIndex: Int, userId: String): List<SportClubSummary>

}