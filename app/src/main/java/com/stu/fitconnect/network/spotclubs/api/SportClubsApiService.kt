package com.stu.fitconnect.network.spotclubs.api

import retrofit2.http.GET
import retrofit2.http.Query

interface SportClubsApiService {
    @GET("")
    suspend fun getSummarySportClubList(
        @Query("userId") userId: String,
        @Query("filters") filters: SportClubsFilterRequest,
        @Query("search_by") searchBy: String,
        @Query("page_index") pageIndex: Int
    ): SportsClubSummaryListResponse
}