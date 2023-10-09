package com.stu.fitconnect.features.sportsclubs.presentation.list

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.UnidirectionalViewModel
import com.stu.fitconnect.features.sportsclubs.domain.Filter
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFiltersData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface SportClubListContract:
    UnidirectionalViewModel<SportClubListContract.State, SportClubListContract.Event> {

    data class State(
        val pagingSportsClubList : Flow<PagingData<SportClubSummary>> = emptyFlow(),
        val searchText: String = "",
        val selectedFilters: SportsClubsFiltersData = SportsClubsFiltersData(),
        val refreshing: Boolean = false,
        val isLoading: Boolean = false,
    )

    sealed class Event {
        object OnRefresh : Event()
        object OnGetSportsClubFilters : Event()
        object OnGetSportsClub : Event()
        data class OnSearchSportsClub(val searchBy: String) : Event()
        data class OnApplySingleFilter(val filter: Filter) : Event()
        data class OnApplySelectedFilters(val sportsClubsFilters: SportsClubsFiltersData) : Event()
    }
}
