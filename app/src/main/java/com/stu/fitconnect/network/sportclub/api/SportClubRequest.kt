package com.stu.fitconnect.network.sportclub.api

import com.google.gson.annotations.SerializedName

data class SportClubRequest (
    @SerializedName("club_id")  val id: Int,
    @SerializedName("token") val userId: String,
    )
