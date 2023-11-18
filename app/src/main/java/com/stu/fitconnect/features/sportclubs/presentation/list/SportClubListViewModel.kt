package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.features.sportclubs.domain.usecases.GetSportClubsListUseCase
import com.stu.fitconnect.features.sportclubs.presentation.FiltersManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportClubListViewModel @Inject constructor(
    private val getSportClubsListUseCase: GetSportClubsListUseCase,
    private val filtersManager: FiltersManager
): ViewModel(), SportClubListContract {

    private val mutableScreenState = MutableStateFlow(SportClubListContract.State())
    override val state: StateFlow<SportClubListContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SportClubListContract.Event) {
        when (event) {
            SportClubListContract.Event.OnRefresh -> onRefresh()
            SportClubListContract.Event.OnGetSportClubFilters -> getAllSportClubFiltersData()
            SportClubListContract.Event.OnGetSportClub -> getSportsClubsPagingList()
            is SportClubListContract.Event.OnSearchSportClub -> updateStateAndFetchData { it.copy(searchText = event.searchBy) }
            is SportClubListContract.Event.OnSelectFilter -> {
                filtersManager.setSingleFilter(event.filter)
                onRefresh()
            }
        }
    }

    init {
        collectFilterDataAndUpdateList()
    }

    private fun collectFilterDataAndUpdateList() {
        viewModelScope.launch {
            state
                .map { it.selectedFilters }
                .distinctUntilChanged()
                .collect {
                    getAllSportClubFiltersData()
                    onRefresh()
                }
        }
    }

    private fun getAllSportClubFiltersData() {
        mutableScreenState.update {
            it.copy(selectedFilters = filtersManager.filtersState.value)
        }
    }

    private fun getSportsClubsPagingList() = viewModelScope.launch(Dispatchers.IO) {
        mutableScreenState.update { it.copy(isLoading = true) }

        try {
            val pagingList = getSportClubsListUseCase.getSportsClubsPagingList(
                mutableScreenState.value.searchText,
                filtersManager.filtersState.value
            )
            mutableScreenState.update { it.copy(pagingSportClubList = pagingList.cachedIn(viewModelScope)) }
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