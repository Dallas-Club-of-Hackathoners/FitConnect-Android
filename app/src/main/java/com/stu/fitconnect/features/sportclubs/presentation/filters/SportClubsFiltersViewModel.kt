package com.stu.fitconnect.features.sportclubs.presentation.filters

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportclubs.domain.FilterCategory
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class SportClubsFiltersViewModel : ViewModel() {

    fun getAllSportClubFiltersData() : List<FilterCategory> {
        val inputStream = Resources.getSystem().openRawResource(R.raw.sport_clubs_filters_data)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, Array<FilterCategory>::class.java).asList()
    }

}