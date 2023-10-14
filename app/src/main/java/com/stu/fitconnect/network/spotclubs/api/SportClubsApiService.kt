package com.stu.fitconnect.network.spotclubs.api

import retrofit2.http.POST
import retrofit2.http.Query

interface SportClubsApiService {
    @POST("clubs/get_list")
    suspend fun getSummarySportClubList(
        @Query("token") userId: String,
        @Query("clubs_filters") filters: SportClubsFilterRequest,
        @Query("search_by") searchBy: String,
        @Query("page_index") pageIndex: Int
    ): List<SportClubSummaryResponse>
}