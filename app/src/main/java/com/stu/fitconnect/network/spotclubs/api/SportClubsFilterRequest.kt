package com.stu.fitconnect.network.spotclubs.api

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.FilterType
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import retrofit2.http.Query


data class SportClubsSummaryRequest(
    @SerializedName("clubs_filters") val clubsFilters: SportClubsFilterRequest,
    @SerializedName("search_by") val searchBy: String,
    @SerializedName("page_index") val pageIndex: Int,
    @SerializedName("token") val token: String
)

data class SportClubsFilterRequest(
    @SerializedName("favourites") var isFavourite: Boolean = false,
    @SerializedName("facilities") val facilitiesIds: MutableList<Int> = mutableListOf(),
    @SerializedName("cost") val costIds: MutableList<Int> = mutableListOf(),
    @SerializedName("clubsCategory") val clubsCategoryIds: MutableList<Int> = mutableListOf(),
    @SerializedName("sortsType") var sortTypesId: Int = 0
)

fun SportClubsFiltersData.toSportClubsFilterRequest() : SportClubsFilterRequest {
    val sportClubsFilterRequest = SportClubsFilterRequest()
    this.filtersCategoryList.forEach { filterCategory ->
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