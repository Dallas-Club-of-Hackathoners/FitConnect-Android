package com.stu.fitconnect.network.sportclub.source

import com.stu.fitconnect.features.sportclubs.domain.SportClub

interface SportClubSource {

    suspend fun getSportClubInfo(id:Int, userId:String): SportClub
}