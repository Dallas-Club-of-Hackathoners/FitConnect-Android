package com.stu.fitconnect.features.sportclubs.presentation.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu.fitconnect.features.sportclubs.domain.entity.Filter
import com.stu.fitconnect.features.sportclubs.presentation.FiltersManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportClubsFiltersViewModel @Inject constructor(
    private val filtersManager: FiltersManager
) : ViewModel(), SportClubsFilterContract {

    private val mutableScreenState = MutableStateFlow(SportClubsFilterContract.State())
    override val state: StateFlow<SportClubsFilterContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SportClubsFilterContract.Event) {
        when (event) {
            SportClubsFilterContract.Event.OnClearAllFilters -> {}
            SportClubsFilterContract.Event.OnGetAllFilters -> getAllSportClubFiltersData()
            is SportClubsFilterContract.Event.OnApplyFilters -> applyFilters(event.onNavigateBack)
            is SportClubsFilterContract.Event.OnSelectFilter -> selectFilter(event.filter)
        }
    }

    private fun getAllSportClubFiltersData() = viewModelScope.launch {
        mutableScreenState.update { it.copy(filtersData = filtersManager.filtersState.value) }
    }

    private fun applyFilters(onNavigateBack: () -> Unit) = viewModelScope.launch {
        filtersManager.setFilters(mutableScreenState.value.filtersData)
        onNavigateBack()
    }

    private fun selectFilter(filter: Filter) = viewModelScope.launch {
        val filterData = mutableScreenState.value.filtersData.getUpdatedFiltersList(filter)
        mutableScreenState.update { it.copy(filtersData = filterData) }
    }

}