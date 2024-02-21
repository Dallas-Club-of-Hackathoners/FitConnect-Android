package com.stu.fitconnect.network.sportclub.api.requests

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.entity.FilterType
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData


data class SportClubsSummaryRequest(
    @SerializedName("clubs_filters") val clubsFilters: SportClubsFilterRequest?,
    @SerializedName("search_by") val searchBy: String,
    @SerializedName("page_index") val pageIndex: Int,
    @SerializedName("token") val token: String
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