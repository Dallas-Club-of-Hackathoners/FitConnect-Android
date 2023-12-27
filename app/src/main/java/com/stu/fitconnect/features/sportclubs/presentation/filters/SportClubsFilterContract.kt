package com.stu.fitconnect.features.sportclubs.presentation.filters

import androidx.compose.runtime.Stable
import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.sportclubs.domain.entity.Filter
import com.stu.fitconnect.features.sportclubs.domain.entity.FilterCategory
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import kotlinx.collections.immutable.ImmutableList

@Stable
interface SportClubsFilterContract : UnidirectionalViewModel<SportClubsFilterContract.State, SportClubsFilterContract.Event> {

    @Stable
    data class State(
        val filtersData: SportClubsFiltersData = SportClubsFiltersData(emptyList())
    )

    @Stable
    sealed class Event {
        object OnGetAllFilters : Event()
        object OnClearAllFilters : Event()
        data class OnApplyFilters(val onNavigateBack: () -> Unit) : Event()
        data class OnSelectFilter(val filter: Filter) : Event()
    }
}