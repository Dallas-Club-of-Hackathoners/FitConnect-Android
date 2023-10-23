package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.sportclubs.domain.entity.AmenityWithAvailable
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub

interface SportClubInfoContract:
    UnidirectionalViewModel<SportClubInfoContract.State, SportClubInfoContract.Event> {

    data class State(
        val isLoading: Boolean = false,
        val sportClub: SportClub? = null,
        val amenities: List<AmenityWithAvailable> = emptyList()
    )

    sealed class Event {
        data class OnGetSportClub(val id: Int) : Event()
    }
}
