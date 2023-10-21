package com.stu.fitconnect.network.sportclub.api

import com.stu.fitconnect.network.sportclub.api.requests.SportClubSummaryRequest
import com.stu.fitconnect.network.sportclub.api.response.SportClubSummaryResponse
import com.stu.fitconnect.network.sportclub.api.requests.SportClubsSummaryRequest
import com.stu.fitconnect.network.sportclub.api.response.SportClubResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SportClubApiService {
    @POST("clubs/getFullInfo") //todo change to get_club_info
    suspend fun getSportClubInfo(
        @Body request: SportClubSummaryRequest
    ): SportClubResponse

    @POST("clubs/get_list")
    suspend fun getSummarySportClubList(
        @Body request: SportClubsSummaryRequest,
    ): List<SportClubSummaryResponse>
}