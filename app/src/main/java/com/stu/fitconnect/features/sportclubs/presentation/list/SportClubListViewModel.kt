package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.base.ResourceManager
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import com.stu.fitconnect.features.sportclubs.domain.usecases.GetSportClubsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportClubListViewModel @Inject constructor(
    private val getSportClubsListUseCase: GetSportClubsListUseCase,
    private val resourceManager: ResourceManager
): ViewModel(), SportClubListContract {

    private val mutableScreenState = MutableStateFlow(SportClubListContract.State())
    override val state: StateFlow<SportClubListContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SportClubListContract.Event) = when (event) {
        SportClubListContract.Event.OnRefresh -> onRefresh()
        SportClubListContract.Event.OnGetSportClubFilters -> getAllSportClubFiltersData()
        SportClubListContract.Event.OnGetSportClub -> getSportsClubsPagingList()
        is SportClubListContract.Event.OnSearchSportClub -> updateStateAndFetchData { it.copy(searchText = event.searchBy) }
        is SportClubListContract.Event.OnApplySingleFilter -> updateStateAndFetchData { it.copy(selectedFilters = it.selectedFilters.getUpdatedFiltersList(event.filter)) }
        is SportClubListContract.Event.OnApplySelectedFilters -> updateStateAndFetchData { it.copy(selectedFilters = event.sportsClubsFilters) }
    }

    private fun getAllSportClubFiltersData() {
        val jsonString = resourceManager.getRawJsonAsString(R.raw.sport_clubs_filters_data)
        mutableScreenState.update {
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
                mutableScreenState.update { it.copy(pagingSportClubList = pagingList) }
            }
        } catch (e: Exception) {
            // todo handle exception
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