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
    @SerializedName("images_urls") val imagesRes: List<String>,
    @SerializedName("location") val location: AppLocationResponse,
    @SerializedName("score") val score: Double,
    @SerializedName("reviews_count") val reviewsCount: Int,
    @SerializedName("cost") val cost: Int,
    @SerializedName("isFavorite") val isFavorite:Boolean
) {
    fun toSportClubSummary() : SportClubSummary{
        return SportClubSummary(id, name, imagesRes, location.toAppLocation(), score, reviewsCount, cost, isFavorite)
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

