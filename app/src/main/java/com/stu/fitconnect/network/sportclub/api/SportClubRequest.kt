package com.stu.fitconnect.network.sportclub.api

import com.google.gson.annotations.SerializedName

data class SportClubRequest (
    @SerializedName("id")  val id: Int,
    @SerializedName("token") val userId: String,
    )
