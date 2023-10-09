package com.stu.fitconnect.features.sportactivities.presentation.list

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivityFilters
import com.stu.fitconnect.features.sportactivities.domain.usecases.GetSportClubsActivitiesListUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class SportClubActivityListViewModel @Inject constructor(
    private val getAllSportClubsActivitiesListUseCase: GetSportClubsActivitiesListUseCase
):ViewModel(), SportClubActivityListContract {

    private val mutableScreenState = MutableStateFlow(SportClubActivityListContract.State())
    override val state: StateFlow<SportClubActivityListContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SportClubActivityListContract.Event) = when(event) {
        SportClubActivityListContract.Event.OnRefresh -> onRefresh()
        SportClubActivityListContract.Event.OnGetSportClubActivityFilters -> getAllSportClubActivityFiltersData()
        SportClubActivityListContract.Event.OnGetSportClubActivity -> getSportsClubsActivitiesPagingList()
        is SportClubActivityListContract.Event.OnSearchSportClubActivity -> updateStateAndFetchData { it.copy(searchText = event.searchBy) }
        is SportClubActivityListContract.Event.OnApplySingleFilter -> updateStateAndFetchData { it.copy(selectedFilters = it.selectedFilters.getUpdatedFiltersList(event.filter)) }
        is SportClubActivityListContract.Event.OnApplySelectedFilters -> updateStateAndFetchData { it.copy(selectedFilters = event.sportsClubsFilters) }
    }

    private fun getAllSportClubActivityFiltersData() {
        val inputStream = Resources.getSystem().openRawResource(R.raw.test_sport_clubs_activities_filter_data)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        mutableScreenState.update{
            it.copy(selectedFilters = Gson().fromJson(jsonString, SportClubActivityFilters::class.java))
        }
    }

    private fun getSportsClubsActivitiesPagingList() {
        mutableScreenState.update { it.copy(isLoading = true) }
        try {
            viewModelScope.launch {
                val pagingList = getAllSportClubsActivitiesListUseCase.getSportClubsActivitiesPagingList(
                    mutableScreenState.value.searchText,
                    mutableScreenState.value.selectedFilters)
                mutableScreenState.update { it.copy(pagingSportsClubActivityList = pagingList) }
            }
        } catch (e: Exception) {
            //handle exception
            // add side effect?
        } finally {
            mutableScreenState.update { it.copy(isLoading = false) }
        }
    }

    private fun updateStateAndFetchData(updateAction: (SportClubActivityListContract.State) -> SportClubActivityListContract.State) {
        mutableScreenState.update(updateAction)
        getSportsClubsActivitiesPagingList()
    }

    private fun onRefresh() {

        mutableScreenState.update { it.copy(refreshing = true) }
        getSportsClubsActivitiesPagingList()
        mutableScreenState.update { it.copy(refreshing = false) }
    }
}