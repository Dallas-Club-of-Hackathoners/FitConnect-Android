package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu.fitconnect.features.sportclubs.domain.usecases.GetSportClubsListUseCase
import com.stu.fitconnect.utils.ResourceJsonManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportClubListViewModel @Inject constructor(
    private val getSportClubsListUseCase: GetSportClubsListUseCase,
    private val resourceJsonManager: ResourceJsonManager
): ViewModel(), SportClubListContract {

    private val mutableScreenState = MutableStateFlow(SportClubListContract.State())
    override val state: StateFlow<SportClubListContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SportClubListContract.Event) {
        when (event) {
            SportClubListContract.Event.OnRefresh -> onRefresh()
            SportClubListContract.Event.OnGetSportClubFilters -> getAllSportClubFiltersData()
            SportClubListContract.Event.OnGetSportClub -> getSportsClubsPagingList()
            is SportClubListContract.Event.OnSearchSportClub -> updateStateAndFetchData { it.copy(searchText = event.searchBy) }
            is SportClubListContract.Event.OnApplySelectedFilters -> updateStateAndFetchData { it.copy(selectedFilters = event.sportsClubsFilters) }
            else -> {}
        }
    }

    private fun getAllSportClubFiltersData() = viewModelScope.launch(Dispatchers.IO) {
        mutableScreenState.update {
            it.copy(selectedFilters = resourceJsonManager.getFilters())
        }
    }

    private fun getSportsClubsPagingList() = viewModelScope.launch {

        mutableScreenState.update { it.copy(isLoading = true) }
        try {
            //delay(2000)
            val pagingList = getSportClubsListUseCase.getSportsClubsPagingList(
                mutableScreenState.value.searchText,
                mutableScreenState.value.selectedFilters
            )
            mutableScreenState.update { it.copy(pagingSportClubList = pagingList) }
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

    private fun onRefresh() = viewModelScope.launch {
        mutableScreenState.update { it.copy(refreshing = true) }
        getSportsClubsPagingList().join()
        mutableScreenState.update { it.copy(refreshing = false) }
    }
}