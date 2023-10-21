package com.stu.fitconnect.network.sportclub.api.requests

import com.google.gson.annotations.SerializedName

data class SportClubSummaryRequest (
    @SerializedName("club_id") val id: Int,
    @SerializedName("token") val userId: String
)
