package com.stu.fitconnect.features.sportclubs.repositories

import com.stu.fitconnect.features.sportclubs.domain.SportClub

interface SportClubRepository {
     suspend fun getSportClubById(id: Int): SportClub

}