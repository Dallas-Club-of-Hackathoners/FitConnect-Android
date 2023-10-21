package com.stu.fitconnect.network.sportclub.api.response

import com.google.gson.annotations.SerializedName
import com.stu.fitconnect.features.sportclubs.domain.entity.AppLocation

data class AppLocationResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("metro") val metro: String
) {
    fun toAppLocation(): AppLocation {
        return AppLocation(latitude, longitude, address, city, metro)
    }
}
