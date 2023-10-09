package com.stu.fitconnect.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest


data class StateDispatch<STATE, EVENT> (
    val state: STATE,
    val dispatch: (EVENT) -> Unit,
)

data class StateEffectDispatch<STATE, EVENT, EFFECT>(
    val state: STATE,
    val dispatch: (EVENT) -> Unit,
    val effectFlow: Flow<EFFECT>,
)

@Composable
inline fun <reified STATE, EVENT> use(
    viewModel: UnidirectionalViewModel<STATE, EVENT>,
): StateDispatch<STATE, EVENT> {
    val state by viewModel.state.collectAsState()

    val dispatch: (EVENT) -> Unit = { event ->
        viewModel.event(event)
    }
    return StateDispatch(
        state = state,
        dispatch = dispatch,
    )
}

@Composable
inline fun <reified BASE_STATE, BASE_EVENT, BASE_EFFECT> useBase(
    viewModel: BaseUnidirectionalViewModel<BASE_STATE, BASE_EVENT, BASE_EFFECT>,
): StateEffectDispatch<BASE_STATE, BASE_EVENT, BASE_EFFECT> {
    val state by viewModel.baseState.collectAsState()

    val dispatch: (BASE_EVENT) -> Unit = { event ->
        viewModel.baseEvent(event)
    }
    return StateEffectDispatch(
        state = state,
        dispatch = dispatch,
        effectFlow = viewModel.baseEffect,
    )
}

interface UnidirectionalViewModel<STATE, EVENT> {
    val state: StateFlow<STATE>
    fun event(event: EVENT)
}

interface BaseUnidirectionalViewModel<BASE_STATE, BASE_EVENT, BASE_EFFECT> {
    val baseState: StateFlow<BASE_STATE>
    val baseEffect: Flow<BASE_EFFECT>
    fun baseEvent(event: BASE_EVENT)
}

@Suppress("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit) {
    val flow = this
    LaunchedEffect(key1 = flow) {
        flow.collectLatest(function)
    }
}