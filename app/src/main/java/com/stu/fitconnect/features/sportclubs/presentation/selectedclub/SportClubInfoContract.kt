package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.sportclubs.domain.SportClub
import kotlinx.coroutines.flow.emptyFlow

interface SportClubInfoContract:
    UnidirectionalViewModel<SportClubInfoContract.State, SportClubInfoContract.Event> {

    data class State(
        val isLoading: Boolean = false,
        val id: Int = 0,
        val sportClub: SportClub = SportClub(),
    )

    sealed class Event {
//        object OnRefresh : Event()
        object OnGetSportClub : Event()
    }
}
