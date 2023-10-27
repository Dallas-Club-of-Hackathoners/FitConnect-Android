package com.stu.fitconnect.test

import androidx.paging.PagingData
import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.sportclubs.presentation.list.SportClubListContract

interface TestContract:
    UnidirectionalViewModel<TestContract.State, TestContract.Event> {

    data class State(
        val textValue: String = "init"
    )

    sealed class Event {
        object OnSome : Event()
    }
}