package com.stu.fitconnect.network.sportclub.api.response

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubAdmin


data class SportClubResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images_urls") val imagesRes: String,
    @SerializedName("location") val location: AppLocationResponse,
    @SerializedName("rating") val score: Double,
    @SerializedName("admin") val contacts: SportClubAdminResponse,
    @SerializedName("description") val description: String,
    @SerializedName("facilities") val facilities: String,
    @SerializedName("rating_count") val reviewsCount: Int,
    @SerializedName("cost") val cost: String,
    @SerializedName("category") val category: String,
    @SerializedName("isFavorite") val isFavorite: Boolean

) {
    fun toSportClub(): SportClub {
        return SportClub(
            id,
            name,
            listOf(imagesRes),
            location.toAppLocation(),
            score,
            contacts.toSportClubAdmin(),
            description,
            listOf( facilities),
            reviewsCount,
            cost,
            category,
            isFavorite
        )
    }
}

data class SportClubAdminResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
) {
    fun toSportClubAdmin(): SportClubAdmin {
        return SportClubAdmin(id, name, phone)
    }
}
