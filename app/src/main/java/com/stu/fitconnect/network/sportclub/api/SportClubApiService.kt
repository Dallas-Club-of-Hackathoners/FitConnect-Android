package com.stu.fitconnect.network.sportclub.api

import retrofit2.http.Body
import retrofit2.http.POST

interface SportClubApiService {
    @POST("clubs/getFullInfo") //todo change to get_club_info
    suspend fun getSportClubInfo(
        @Body request: SportClubRequest,
    ): SportClubResponse
}