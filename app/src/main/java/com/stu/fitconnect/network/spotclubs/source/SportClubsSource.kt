package com.stu.fitconnect.network.spotclubs.source

import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData


interface SportClubsSource {

    suspend fun getSummarySportClubsPagingList(filters: SportClubsFiltersData, searchBy: String, pageIndex: Int, userId: String): List<SportClubSummary>

}