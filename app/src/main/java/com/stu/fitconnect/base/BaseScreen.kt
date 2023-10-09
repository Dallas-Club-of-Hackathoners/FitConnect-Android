package com.stu.fitconnect.base

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class BaseScreen {

    @Composable
    fun BaseRoute(
        baseViewModel: BaseViewModel,
        content: @Composable () -> Unit,
    ) {
        val (baseState, baseEvent, baseEffect) = useBase(viewModel = baseViewModel)
        val context = LocalContext.current
        val activity = context as? Activity

        baseEffect.collectInLaunchedEffect { effect ->
            when (effect) {
                BaseContract.BaseEffect.OnBackPressed -> {
                    activity?.onBackPressed()
                }
            }
        }
        content()
    }
}