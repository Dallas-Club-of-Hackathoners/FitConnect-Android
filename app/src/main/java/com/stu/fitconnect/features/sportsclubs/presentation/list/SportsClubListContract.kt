package com.stu.fitconnect.features.sportsclubs.presentation.list

import androidx.paging.PagingData
import com.stu.fitconnect.features.sportsclubs.domain.SortType
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import com.stu.fitconnect.UnidirectionalViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface SportsClubListContract:
    UnidirectionalViewModel<SportsClubListContract.State, SportsClubListContract.Event> {

    data class State(
        val pagingSportsClubList : Flow<PagingData<SportClubSummary>> = emptyFlow(),
        val searchText: String = "",
        val selectedFilters: SportsClubsFilters = SportsClubsFilters(),
        val refreshing: Boolean = false,
        val isLoading: Boolean = false,
    )

    sealed class Event {
        data class OnSearchSportsClub(val searchBy: String) : Event()
        data class OnSortSelected(val sortType: SortType) : Event()
        data class OnApplySelectedFilters(val sportsClubsFilters: SportsClubsFilters) : Event()
        object OnRefresh : Event()
    }
}
