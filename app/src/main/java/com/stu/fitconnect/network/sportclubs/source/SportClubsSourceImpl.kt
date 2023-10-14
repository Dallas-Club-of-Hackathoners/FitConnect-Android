package com.stu.fitconnect.network.sportclubs.source

import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import com.stu.fitconnect.network.sportclubs.api.SportClubsApiService
import com.stu.fitconnect.network.sportclubs.api.SportClubsSummaryRequest
import com.stu.fitconnect.network.sportclubs.api.toSportClubsFilterRequest
import com.stu.fitconnect.network.sportclubs.api.toSportsClubSummaryList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportClubsSourceImpl @Inject constructor(
    private val sportsClubsApiService: SportClubsApiService
): SportClubsSource {

    override suspend fun getSummarySportClubsPagingList(filters: SportClubsFiltersData, searchBy: String, pageIndex: Int, userId: String): List<SportClubSummary> {
        return try {
            sportsClubsApiService.getSummarySportClubList(
                SportClubsSummaryRequest(
                    clubsFilters = filters.toSportClubsFilterRequest(),
                    searchBy = searchBy,
                    pageIndex = pageIndex,
                    token = userId
                )
            ).toSportsClubSummaryList()
        } catch (e: Exception) {
            throw e
        }
    }

}