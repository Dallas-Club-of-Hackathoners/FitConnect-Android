package com.stu.fitconnect.network.sportclub.source

import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.network.sportclub.api.SportClubApiService
import com.stu.fitconnect.network.sportclub.api.requests.SportClubSummaryRequest
import com.stu.fitconnect.network.sportclub.api.requests.SportClubsSummaryRequest
import com.stu.fitconnect.network.sportclub.api.requests.toSportClubsFilterRequest
import com.stu.fitconnect.network.sportclub.api.response.toSportsClubSummaryList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportClubSourceImpl @Inject constructor(
    private val sportClubApiService: SportClubApiService
) : SportClubSource {

    override suspend fun getSportClubFullInfo(id: Int, userId: String): SportClub {
        return try {
            sportClubApiService.getSportClubInfo(
                SportClubSummaryRequest(
                    id = id,
                    userId = userId
                )
            ).toSportClub()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getSummarySportClubsPagingList(filters: SportClubsFiltersData, searchBy: String, pageIndex: Int, userId: String): List<SportClubSummary> {
        return try {
            sportClubApiService.getSummarySportClubList(
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