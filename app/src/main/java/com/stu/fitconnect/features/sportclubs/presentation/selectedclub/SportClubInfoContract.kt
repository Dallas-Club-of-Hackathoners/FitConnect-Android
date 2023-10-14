package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import androidx.paging.PagingData
import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface SportClubInfoContract:
    UnidirectionalViewModel<SportClubInfoContract.State, SportClubInfoContract.Event> {

    data class State(
        val isLoading: Boolean = false,
    )

    sealed class Event {
        object OnRefresh : Event()
        object OnGetSportClub : Event()
    }
}
