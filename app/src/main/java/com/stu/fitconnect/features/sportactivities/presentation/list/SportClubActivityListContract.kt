package com.stu.fitconnect.features.sportactivities.presentation.list

import androidx.paging.PagingData
import com.stu.fitconnect.UnidirectionalViewModel
import com.stu.fitconnect.features.sportactivities.domain.FilterActivities
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivityFilters
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivitySummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface SportClubActivityListContract:
    UnidirectionalViewModel<SportClubActivityListContract.State, SportClubActivityListContract.Event> {

    data class State(
        val pagingSportsClubActivityList : Flow<PagingData<SportClubActivitySummary>> = emptyFlow(),
        val searchText: String = "",
        val selectedFilters: SportClubActivityFilters = SportClubActivityFilters(),
        val refreshing: Boolean = false,
        val isLoading: Boolean = false,
    )

    sealed class Event {
        object OnRefresh : Event()
        object OnGetSportClubActivityFilters : Event()
        object OnGetSportClubActivity : Event()
        data class OnSearchSportClubActivity(val searchBy: String) : Event()
        data class OnApplySingleFilter(val filter: FilterActivities) : Event()
        data class OnApplySelectedFilters(val sportsClubsFilters: SportClubActivityFilters) : Event()
    }
}
