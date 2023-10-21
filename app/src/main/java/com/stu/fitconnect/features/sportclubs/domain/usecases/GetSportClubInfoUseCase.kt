package com.stu.fitconnect.features.sportclubs.domain.usecases

import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
import com.stu.fitconnect.features.sportclubs.repositories.SportClubRepository
import javax.inject.Inject

class GetSportClubInfoUseCase @Inject constructor(
    private val sportClubRepository: SportClubRepository
){
    suspend fun getSportClubById(id: Int): SportClub {
        return sportClubRepository.getSportClubById(id)
    }
}
public const val a = 20
class Person() {
    val brr = a
}

public class Student() {
    public val per = Person()
    public val b = per.brr
}
