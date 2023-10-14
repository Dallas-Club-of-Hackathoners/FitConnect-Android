package com.stu.fitconnect.network.sportclub.api

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.features.sportclubs.domain.SportClubAdmin
import com.stu.fitconnect.network.sportclubs.api.AppLocationResponse

data class SportClubResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images_urls") val imagesRes: String,
    @SerializedName("location") val location: AppLocationResponse,
    @SerializedName("rating") val score: Double,
    @SerializedName("contacts") val contacts: SportClubAdmin,
    @SerializedName("description") val description: String,
    @SerializedName("facilities") val facilities: List<String>,
    @SerializedName("rating_count") val reviewsCount: Int,
    @SerializedName("cost") val cost: String,
    @SerializedName("isFavorite") val isFavorite: Boolean

) {
    fun toSportClub(): SportClub{
        return SportClub(
            id,
            name,
            listOf(imagesRes),
            location.toAppLocation(),
            score,
            contacts,
            description,
            facilities,
            reviewsCount,
            cost,
            isFavorite
        )
    }
}
