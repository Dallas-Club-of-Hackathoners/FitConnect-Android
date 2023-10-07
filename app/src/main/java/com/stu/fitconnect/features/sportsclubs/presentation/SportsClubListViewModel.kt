package com.stu.fitconnect.features.sportsclubs.presentation

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class SportsClubListViewModel: ViewModel() {

    fun getSportsClubFiltersUseCase(): SportsClubsFilters {
        val inputStream = Resources.getSystem().openRawResource(R.raw.sports_clubs_filters)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        return Gson().fromJson(jsonString, SportsClubsFilters::class.java)
    }

}