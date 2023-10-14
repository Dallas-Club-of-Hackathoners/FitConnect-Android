package com.stu.fitconnect.network.spotclubs.api

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary

//data class SportsClubSummaryListResponse(
//    @SerializedName("sport_clubs_list") val sportsClubSummaryList: List<SportClubSummaryResponse>,
//) {
//
//}

fun List<SportClubSummaryResponse>.toSportsClubSummaryList() : List<SportClubSummary> {
    val list : MutableList<SportClubSummary> = mutableListOf()
    this.forEach {
        list.add(it.toSportClubSummary())
    }
    return list
}

data class SportClubSummaryResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images_urls") val imagesRes: String,
    @SerializedName("location") val location: AppLocationResponse,
    @SerializedName("rating") val score: Double,
    @SerializedName("rating_count") val reviewsCount: Int,
    @SerializedName("cost") val cost: String,
    @SerializedName("category") val category: String,
    @SerializedName("isFavorite") val isFavorite: Boolean
) {
    fun toSportClubSummary() : SportClubSummary{
        return SportClubSummary(id, name, listOf(imagesRes), location.toAppLocation(), score, reviewsCount, cost, category, isFavorite)
    }
}

data class AppLocationResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("metro") val metro: String
) {
    fun toAppLocation(): AppLocation{
        return AppLocation(latitude, longitude, address, city, metro)
    }
}

