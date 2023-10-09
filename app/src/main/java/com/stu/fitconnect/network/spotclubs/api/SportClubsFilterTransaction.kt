package com.stu.fitconnect.network.spotclubs.api

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.FilterType
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData

data class SportClubsFilterRequest(
    @SerializedName("is_favourite") var isFavourite: Boolean = false,
    @SerializedName("facilities") val facilitiesIds: MutableList<Int> = mutableListOf(),
    @SerializedName("cost") val costIds: MutableList<Int> = mutableListOf(),
    @SerializedName("clubs_category") val clubsCategoryIds: MutableList<Int> = mutableListOf(),
    @SerializedName("sort_types") var sortTypesId: Int = 0
)

fun SportClubsFiltersData.toSportClubsFilterRequest() : SportClubsFilterRequest {
    val sportClubsFilterRequest = SportClubsFilterRequest()
    this.filtersList.forEach { filterCategory ->
        filterCategory.filtersList.forEach { filter ->
            if(filter.isSelect) {
                when(filterCategory.id) {
                    FilterType.IS_FAVOURITE.ordinal -> sportClubsFilterRequest.isFavourite = true
                    FilterType.FACILITIES.ordinal -> sportClubsFilterRequest.facilitiesIds.add(filter.id)
                    FilterType.COST.ordinal -> sportClubsFilterRequest.costIds.add(filter.id)
                    FilterType.CLUB_CATEGORY.ordinal -> sportClubsFilterRequest.clubsCategoryIds.add(filter.id)
                    FilterType.SORT_TYPE.ordinal -> sportClubsFilterRequest.sortTypesId = filter.id
                }
            }
        }
    }
    return sportClubsFilterRequest
}