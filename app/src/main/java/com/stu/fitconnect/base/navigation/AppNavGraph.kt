package com.stu.fitconnect.base.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.stu.fitconnect.features.authentication.presentation.login.LoginScreen
import com.stu.fitconnect.features.authentication.presentation.login.LoginScreenRoute
import com.stu.fitconnect.features.authentication.presentation.signup.SignUpScreenRoute
import com.stu.fitconnect.features.sportclubs.presentation.list.SportsClubsListRoute
import com.stu.fitconnect.features.sportclubs.presentation.selectedclub.SportClubInfoRoute

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    isLogIn: Boolean
) {
    NavHost(
        navController = navHostController,
        startDestination = if(isLogIn) Screen.SportClubSList.route else Screen.SignIn.route
    ) {

        composable(Screen.SignUp.route) {
            SignUpScreenRoute(
                onNavigateToMainScreen = { /*TODO*/ },
                onNavigateToSportClubsListScreen = {
                    navHostController.navigate(
                        route = Screen.SportClubSList.route
                    )
                }
            )
        }
        composable(Screen.SignIn.route) {
            LoginScreenRoute(
                onNavigateToMainScreen = { /*TODO*/ },
                onNavigateToSportClubsListScreen = {
                    navHostController.navigate(
                        route = Screen.SportClubSList.route
                    )
                },
                onNavigateToSignUpScreen = {
                    navHostController.navigate(
                        route = Screen.SignUp.route
                    )
                } ,
            )
        }
        composable(Screen.SportClubSList.route) {
            SportsClubsListRoute(
                onNavigateToDetailSportsClubsScreen = { sportClubId ->
                    navHostController.navigate(
                        route = Screen.SelectedSportClub(sportClubId).getRouteWithArgs()
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
//        composable(
//            route = Screen.ROUTE_SELECTED_SPORT_CLUB,
//            arguments = listOf(navArgument(Screen.KEY_SPORT_CLUB) { type = NavType.IntType })
//        ) {
//            SportClubInfoRoute(
//                sportClubId = it.arguments?.getInt(Screen.KEY_SPORT_CLUB) ?: throw (Exception()),//todo
//                onNavigateToActivitiesTableScreen = {
//                    //todo
//                }
//            )
//        }

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