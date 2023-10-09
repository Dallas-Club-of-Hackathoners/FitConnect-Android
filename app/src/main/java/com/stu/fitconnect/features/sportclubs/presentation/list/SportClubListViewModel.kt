package com.stu.fitconnect.features.sportclubs.presentation.list

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import com.stu.fitconnect.features.sportclubs.domain.usecases.GetSportClubsListUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class SportClubListViewModel @Inject constructor(
    private val getSportClubsListUseCase: GetSportClubsListUseCase
): ViewModel(), SportClubListContract {

    private val mutableScreenState = MutableStateFlow(SportClubListContract.State())
    override val state: StateFlow<SportClubListContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SportClubListContract.Event) = when (event) {
        SportClubListContract.Event.OnRefresh -> onRefresh()
        SportClubListContract.Event.OnGetSportsClubFilters -> getAllSportClubFiltersData()
        SportClubListContract.Event.OnGetSportsClub -> getSportsClubsPagingList()
        is SportClubListContract.Event.OnSearchSportsClub -> updateStateAndFetchData { it.copy(searchText = event.searchBy) }
        is SportClubListContract.Event.OnApplySingleFilter -> updateStateAndFetchData { it.copy(selectedFilters = it.selectedFilters.getUpdatedFiltersList(event.filter)) }
        is SportClubListContract.Event.OnApplySelectedFilters -> updateStateAndFetchData { it.copy(selectedFilters = event.sportsClubsFilters) }
    }

    private fun getAllSportClubFiltersData() {
        val inputStream = Resources.getSystem().openRawResource(R.raw.sport_clubs_filters_data)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        mutableScreenState.update{
            it.copy(selectedFilters = Gson().fromJson(jsonString, SportClubsFiltersData::class.java))
        }
    }

    private fun getSportsClubsPagingList() {
        mutableScreenState.update { it.copy(isLoading = true) }
        try {
            viewModelScope.launch {
                val pagingList = getSportClubsListUseCase.getSportsClubsPagingList(
                    mutableScreenState.value.searchText,
                    mutableScreenState.value.selectedFilters)
                mutableScreenState.update { it.copy(pagingSportsClubList = pagingList) }
            }
        } catch (e: Exception) {
            //handle exception
            // add side effect?
        } finally {
            mutableScreenState.update { it.copy(isLoading = false) }
        }
    }

    private fun updateStateAndFetchData(updateAction: (SportClubListContract.State) -> SportClubListContract.State) {
        mutableScreenState.update { updateAction(it) }
        getSportsClubsPagingList()
    }

    private fun onRefresh() {
        mutableScreenState.update { it.copy(refreshing = true) }
        getSportsClubsPagingList()
        mutableScreenState.update { it.copy(refreshing = false) }
    }
}