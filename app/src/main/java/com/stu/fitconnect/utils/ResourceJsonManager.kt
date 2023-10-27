package com.stu.fitconnect.utils

import android.content.Context
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportclubs.domain.entity.Amenity
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceJsonManager @Inject constructor(
    private val context: Context
) {

    fun getAllAmenities() : List<Amenity> {
        val inputStream = context.resources.openRawResource(R.raw.amenities)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, Array<Amenity>::class.java).asList()
    }

    fun getFilters(): SportClubsFiltersData {
        val inputStream = context.resources.openRawResource(R.raw.sport_clubs_filters_data)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, SportClubsFiltersData::class.java)
    }

}