package com.stu.fitconnect.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.view.ViewCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stu.fitconnect.base.navigation.AppNavGraph
import com.stu.fitconnect.base.navigation.rememberNavigationState
import com.stu.fitconnect.ui.theme.BackgroundColor

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = BackgroundColor
    )

    AppNavGraph(
        navHostController = navigationState.navHostController,
        isLogIn = false
    )
}
