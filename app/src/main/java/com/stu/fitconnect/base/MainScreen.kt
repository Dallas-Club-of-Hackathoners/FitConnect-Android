package com.stu.fitconnect.base

import androidx.compose.runtime.Composable
import com.stu.fitconnect.base.navigation.AppNavGraph
import com.stu.fitconnect.base.navigation.rememberNavigationState

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    AppNavGraph(
        navHostController = navigationState.navHostController,
        isLogIn = false
    )
}