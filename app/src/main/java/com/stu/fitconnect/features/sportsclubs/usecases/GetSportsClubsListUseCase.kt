package com.stu.fitconnect.features.sportsclubs.usecases

import com.stu.fitconnect.features.sportsclubs.repositories.SportsClubRepository

class GetSportsClubsListUseCase(
    private val sportsClubRepository: SportsClubRepository
) {
    operator suspend fun invoke(/* принимает обьект фильтров в каком то виде и строку поиска*/) {
        return sportsClubRepository.getSportsClubList()
    }
}