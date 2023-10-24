package com.stu.fitconnect.network.sportclub.api.response

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary


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
    @SerializedName("image_urls") val imagesRes: String,
    @SerializedName("location") val location: AppLocationResponse,
    @SerializedName("rating") val score: Double,
    @SerializedName("rating_count") val reviewsCount: Int,
    @SerializedName("cost") val cost: Int,
    @SerializedName("category") val category: String,
    @SerializedName("isFavorite") val isFavorite: Boolean
) {
    fun toSportClubSummary() : SportClubSummary {
        return SportClubSummary(id, name, listOf(imagesRes), location.toAppLocation(), score, reviewsCount,category, cost, isFavorite)//todo
    }
}


