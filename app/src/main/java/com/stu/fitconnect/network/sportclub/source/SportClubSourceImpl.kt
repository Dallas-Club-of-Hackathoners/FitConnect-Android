package com.stu.fitconnect.network.sportclub.source

import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.network.sportclub.api.SportClubApiService
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class SportClubSourceImpl @Inject constructor(
//    private val sportClubApiService: SportClubApiService
//) : SportClubSource {
//    override suspend fun getSportClubInfo(id: Int, userId: String): SportClub {
//        return try {
//            sportClubApiService.getSportClubInfo(
//
//            )
//
//
//
//        } catch (e: Exception) {
//            throw e
//        }
//    }
//}