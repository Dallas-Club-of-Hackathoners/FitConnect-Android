package com.stu.fitconnect.base.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

//fun AppNavGraph(
//    navHostController: NavHostController,
//    newsFeedScreenContent: @Composable () -> Unit,
//    favouriteScreenContent: @Composable () -> Unit,
//    profileScreenContent: @Composable () -> Unit,
//    commentsScreenContent: @Composable (FeedPost) -> Unit
//) {
//    NavHost(
//        navController = navHostController,
//        startDestination = Screen.Home.route
//    ) {
//        homeScreenNavGraph(
//            newsFeedScreenContent = newsFeedScreenContent,
//            commentsScreenContent = commentsScreenContent
//        )
//        composable(Screen.Favourite.route) {
//            favouriteScreenContent()
//        }
//        composable(Screen.Profile.route) {
//            profileScreenContent()
//        }
//    }
//}