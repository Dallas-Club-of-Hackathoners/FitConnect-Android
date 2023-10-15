package com.stu.fitconnect.features.sportclubs.repositories

import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.network.authentication.AuthenticationSource
import com.stu.fitconnect.network.sportclub.source.SportClubSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportClubRepositoryImpl @Inject constructor(
    private val sportClubSource: SportClubSource,
    private val authenticationSource: AuthenticationSource
    ): SportClubRepository {
    override suspend fun getSportClubById(id: Int): SportClub {
        val userId = authenticationSource.getCurrentUId()

       return sportClubSource.getSportClubInfo(id, userId)
    }
}