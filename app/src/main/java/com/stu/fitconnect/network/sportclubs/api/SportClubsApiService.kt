package com.stu.fitconnect.network.sportclubs.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SportClubsApiService {
    @POST("clubs/get_list?")
    suspend fun getSummarySportClubList(
        @Body request: SportClubsSummaryRequest,
    ): List<SportClubSummaryResponse>
}