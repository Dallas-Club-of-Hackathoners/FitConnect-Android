package com.stu.fitconnect.base.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.stu.fitconnect.features.sportclubs.presentation.list.SportsClubsListRoute
import com.stu.fitconnect.features.sportclubs.presentation.selectedclub.SportClubInfoRoute

@Composable
fun AppNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SportClubSList.route
    ) {

        composable(Screen.SportClubSList.route) {
            SportsClubsListRoute(
                onNavigateToDetailSportsClubsScreen = { sportClubId ->
                    navHostController.navigate(
                        Screen.SelectedSportClub(sportClubId).getRouteWithArgs()
                    )
                },
                onNavigateToFiltersSportsClubsScreen = {

                }
            )
        }
        composable(
            route = Screen.ROUTE_SELECTED_SPORT_CLUB,
            arguments = listOf(navArgument(Screen.KEY_SPORT_CLUB) { type = NavType.IntType })
        ) {
            SportClubInfoRoute(
                sportClubId = it.arguments?.getInt(Screen.KEY_SPORT_CLUB) ?: throw (Exception()),//todo
                onNavigateToActivitiesTableScreen = {
                    //todo
                }
            )

        }
        composable(
            route = Screen.ROUTE_SELECTED_SPORT_CLUB,
            arguments = listOf(navArgument(Screen.KEY_SPORT_CLUB) { type = NavType.IntType })
        ) {
            SportClubInfoRoute(
                sportClubId = it.arguments?.getInt(Screen.KEY_SPORT_CLUB) ?: throw (Exception()),//todo
                onNavigateToActivitiesTableScreen = {
                    //todo
                }
            )
        }

    }

//    NavHost(
//        navController = navController,
//        startDestination = Destinations.NewsListScreen.route,
//        modifier = modifier,
//    ) {
//        // bottom navigation screens & nested graphs
//        newsListGraph(navController)
//        favoriteNewsGraph(navController)
//        profileGraph(navController)
//
//        // common screens in entire app
//        newsDetailGraph()
//
//        ...
//    }
}