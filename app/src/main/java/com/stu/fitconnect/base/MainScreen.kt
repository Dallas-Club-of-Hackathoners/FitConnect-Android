package com.stu.fitconnect.base

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.stu.fitconnect.base.navigation.AppNavGraph
import com.stu.fitconnect.base.navigation.rememberNavigationState

@Composable
fun MainScreen() {
//    val navigationState = rememberNavigationState()
//    val systemUiController = rememberSystemUiController()
//    systemUiController.setStatusBarColor(
//        color = BackgroundColor
//    )
    val nc = rememberNavController()
    AppNavGraph(
        navHostController = nc,
        isLogIn = true
    )
}
