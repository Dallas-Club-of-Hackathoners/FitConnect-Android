package com.stu.fitconnect.features.sportsclubs.presentation

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportsclubs.domain.ClubsCategory
import com.stu.fitconnect.features.sportsclubs.domain.Cost
import com.stu.fitconnect.features.sportsclubs.domain.Facility
import com.stu.fitconnect.features.sportsclubs.domain.Filter
import com.stu.fitconnect.features.sportsclubs.domain.FilterCategoryData
import com.stu.fitconnect.features.sportsclubs.domain.IsFavouriteFilter
import com.stu.fitconnect.features.sportsclubs.domain.SortType
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import com.stu.fitconnect.features.sportsclubs.domain.usecases.GetSportsClubsListUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import javax.inject.Inject

@ViewModelScoped
class SportsClubListViewModel @Inject constructor(
    private val getSportsClubsListUseCase: GetSportsClubsListUseCase
): ViewModel() {


    private val _screenState =  MutableLiveData<State>(State())
    val screenState: LiveData<State> = _screenState

//    init {
//       // getSportsClubsList("", SportsClubsFilters())
//    }

    fun getSportsClubsList(searchBy: String, sportsClubsFilters: SportsClubsFilters) {
        viewModelScope.launch {
            getSportsClubsListUseCase.getSportsClubsList(searchBy, sportsClubsFilters)
        }
    }


    fun getAllSportsClubFiltersData() : List<FilterCategoryData> {
        val inputStream = Resources.getSystem().openRawResource(R.raw.sports_clubs_filters_data)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, Array<FilterCategoryData>::class.java).asList()
    }

    fun onBasicFilterItemClicked(filter: Filter) {
        try {
            val sportsClubsFilters = filterSelect(filter, _screenState.value?.savedSelectedFilters)
            updateState {
                copy(savedSelectedFilters = sportsClubsFilters)
            }
        } catch (e: Exception) {
            //TODO handle it
        }
    }

    private fun filterSelect(filter: Filter, selectedFilters: SportsClubsFilters?) : SportsClubsFilters {
        when(filter) {
            is IsFavouriteFilter -> selectedFilters?.selectIsFavouriteFilter()
            is Facility -> selectedFilters?.selectFacility(filter)
            is Cost -> selectedFilters?.selectCost(filter)
            is ClubsCategory -> selectedFilters?.selectClubsCategory(filter)
            is SortType -> selectedFilters?.selectSortType(filter)
        }
        throw IllegalStateException("Unexpected filter type")
    }
    private fun updateState(block: State.() -> State) {
        _screenState.value = _screenState.value!!.block()
    }

    data class State(
        val searchText: String = "",
        val savedSelectedFilters: SportsClubsFilters = SportsClubsFilters(),
        val selectedFilters: SportsClubsFilters = SportsClubsFilters()
    )

}