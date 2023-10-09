package com.stu.fitconnect.features.sportactivities.presentation.filters

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportactivities.domain.FilterCategoryActivities
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class SportClubsActivitiesFiltersViewModel:ViewModel() {

    fun getAllSportClubActivitiesFiltersData():List<FilterCategoryActivities> {
        val inputStream = Resources.getSystem().openRawResource(R.raw.test_sport_clubs_activities_filter_data)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, Array<FilterCategoryActivities>::class.java).asList()
    }
}