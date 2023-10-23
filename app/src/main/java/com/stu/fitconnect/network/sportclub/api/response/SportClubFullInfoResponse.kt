package com.stu.fitconnect.network.sportclub.api.response

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.entity.Amenity
import com.stu.fitconnect.features.sportclubs.domain.entity.AmenityWithAvailable
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubAdmin

data class SportClubResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images_urls") val imagesUrls: String,
    @SerializedName("location") val location: AppLocationResponse,
    @SerializedName("rating") val score: Double,
    @SerializedName("admin") val contacts: SportClubAdminResponse,
    @SerializedName("description") val description: String,
    @SerializedName("facilities") val amenitiesIds: List<Int>/*String*/,
    @SerializedName("rating_count") val reviewsCount: Int,
    @SerializedName("cost") val cost: String,
    @SerializedName("category") val category: String,
    @SerializedName("isFavorite") val isFavorite: Boolean
) {
    fun toSportClub(amenitiesList: List<Amenity>): SportClub {
        val amenitiesWithAvailableList = amenitiesList.map { amenity ->
            val available = amenity.id in amenitiesIds
            AmenityWithAvailable(amenity.id, amenity.name, amenity.iconRes, available)
        }
        return SportClub(
            id = id,
            name = name,
            imagesUrls = listOf(imagesUrls),
            location = location.toAppLocation(),
            score = score,
            contacts = contacts.toSportClubAdmin(),
            description = description,
            amenities = amenitiesWithAvailableList,
            reviewsCount =reviewsCount,
            cost = /*cost todo*/ 1,
            category = category,
            isFavorite = isFavorite
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
