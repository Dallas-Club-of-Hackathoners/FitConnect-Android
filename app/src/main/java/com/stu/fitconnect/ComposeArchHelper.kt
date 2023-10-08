package com.stu.fitconnect

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UnidirectionalViewModel<STATE, EVENT> {
    val state: StateFlow<STATE>
    fun event(event: EVENT)
}

interface BaseUnidirectionalViewModel<BASE_EVENT, BASE_EFFECT, BASE_STATE> {
    val baseState: StateFlow<BASE_STATE>
    val baseEffect: Flow<BASE_EFFECT>
    fun baseEvent(event: BASE_EVENT)
}