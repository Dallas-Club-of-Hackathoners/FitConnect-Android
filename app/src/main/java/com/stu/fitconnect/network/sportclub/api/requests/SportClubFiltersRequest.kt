package com.stu.fitconnect.network.sportclub.api.requests

import com.google.gson.annotations.SerializedName

data class SportClubsFilterRequest(
    @SerializedName("favourites") var isFavourite: Boolean = false,
    @SerializedName("facilities") val facilitiesIds: MutableList<Int> = mutableListOf(),
    @SerializedName("cost") val costIds: MutableList<Int> = mutableListOf(),
    @SerializedName("clubsCategory") val clubsCategoryIds: MutableList<Int> = mutableListOf(),
    @SerializedName("sortsType") var sortTypesId: Int = 0
)