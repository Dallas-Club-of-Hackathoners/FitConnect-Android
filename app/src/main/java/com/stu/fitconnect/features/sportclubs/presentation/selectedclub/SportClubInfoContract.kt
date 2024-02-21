package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import androidx.compose.runtime.Stable
import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.sportclubs.domain.entity.AmenityWithAvailable
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
@Stable
interface SportClubInfoContract:
    UnidirectionalViewModel<SportClubInfoContract.State, SportClubInfoContract.Event> {

    @Stable
    data class State(
        val isLoading: Boolean = false,
        val sportClub: SportClub? = null,
        val amenities: List<AmenityWithAvailable> = emptyList()
    )
    @Stable
    sealed class Event {
        data class OnGetSportClub(val id: Int) : Event()
    }
}
