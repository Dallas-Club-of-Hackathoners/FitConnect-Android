package com.stu.fitconnect.features.sportclubs.domain.usecases

import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.features.sportclubs.repositories.SportClubsRepository
import javax.inject.Inject

class GetSportClubInfoUseCase @Inject constructor(
    private val sportClubsRepository: SportClubsRepository
){
    suspend fun getSportClubById(id: Int): SportClub {
        return sportClubsRepository.getSportClubById(id)
    }
}