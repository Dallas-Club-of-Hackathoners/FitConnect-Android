package com.stu.fitconnect.network.sportclub.api.response

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.entity.Amenity
import com.stu.fitconnect.features.sportclubs.domain.entity.AmenityWithAvailable
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubAdmin

data class SportClubResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image_urls") val imagesUrls: String,
    @SerializedName("location") val location: AppLocationResponse,
    @SerializedName("rating") val score: Double,
    @SerializedName("admin") val contacts: SportClubAdminResponse,
    @SerializedName("description") val description: String,
    @SerializedName("rating_count") val reviewsCount: Int,
    @SerializedName("cost") val cost: Int,
    @SerializedName("facilities") val amenitiesIds: String /*List<Int>*/,
    @SerializedName("category") val category: String,
    @SerializedName("isFavorite") val isFavorite: Boolean
) {
    fun toSportClub(amenitiesList: List<Amenity>): SportClub {
        val amenitiesWithAvailableList = mutableListOf<AmenityWithAvailable>()
        amenitiesList.forEach { amenity ->
            val available = /*amenity.id in amenitiesIds.// todo*/ true
            amenitiesWithAvailableList.add(AmenityWithAvailable(amenity.id, amenity.name, amenity.iconRes, available))
        }
        return SportClub(
            id = id,
            name = name,
            imagesUrls = imagesUrls.split("\n"),
            location = location.toAppLocation(),
            score = score,
            contacts = contacts.toSportClubAdmin(),
            description = description,
            amenities = amenitiesWithAvailableList,
            reviewsCount =reviewsCount,
            cost = /*cost todo*/ cost,
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
