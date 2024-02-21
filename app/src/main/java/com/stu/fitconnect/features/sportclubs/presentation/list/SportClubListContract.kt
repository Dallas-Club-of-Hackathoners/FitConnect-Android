package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.sportclubs.domain.entity.Filter
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
interface SportClubListContract: UnidirectionalViewModel<SportClubListContract.State, SportClubListContract.Event> {

    @Stable
    data class State(
        val pagingSportClubList : Flow<PagingData<SportClubSummary>> = emptyFlow(),
        val searchText: String = "",
        val selectedFilters: SportClubsFiltersData? = null,
        val errorMessages: List<String> = emptyList(),
        val refreshing: Boolean = false,
        val isLoading: Boolean = true,
    )
    @Stable
    sealed class Event {
        object OnRefresh : Event()
        object OnGetSportClubFilters : Event()
        object OnGetSportClub : Event()
        data class OnSearchSportClub(val searchBy: String) : Event()
        data class OnSelectFilter(val filter: Filter) : Event()
    }
}