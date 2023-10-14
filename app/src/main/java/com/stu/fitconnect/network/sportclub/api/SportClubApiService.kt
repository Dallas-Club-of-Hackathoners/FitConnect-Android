package com.stu.fitconnect.network.sportclub.api

import com.stu.fitconnect.features.sportclubs.domain.SportClub
import retrofit2.http.Body
import retrofit2.http.POST

interface SportClubApiService {
    @POST("clubs/get_list?") //todo change to get_club_info
    suspend fun getSportClubInfo(
        @Body request: SportClubRequest,
    ): SportClubResponse
}