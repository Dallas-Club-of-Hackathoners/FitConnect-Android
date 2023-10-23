package com.stu.fitconnect.utils

import android.content.Context
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportclubs.domain.entity.Amenity
import javax.inject.Inject

class ResourceJsonManager @Inject constructor(
    private val context: Context
) {

    fun getAllAmenities() : List<Amenity> {
        val inputStream = context.resources.openRawResource(R.raw.amenities)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, Array<Amenity>::class.java).asList()
    }
}