package com.stu.fitconnect.base.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.stu.fitconnect.features.authentication.presentation.login.LoginScreenRoute
import com.stu.fitconnect.features.authentication.presentation.signup.SignUpScreenRoute
import com.stu.fitconnect.features.sportclubs.presentation.filters.SportClubsFiltersRoute
import com.stu.fitconnect.features.sportclubs.presentation.list.SportsClubsListRoute
import com.stu.fitconnect.features.sportclubs.presentation.selectedclub.SportClubInfoRoute
import com.stu.fitconnect.test.TestScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    isLogIn: Boolean
) {
    NavHost(
        navController = navHostController,
        startDestination = /*"test"*/ if(isLogIn) Screen.SportClubList.route else Screen.SignIn.route
    ) {
        composable(route = "test") {

            //val viewModel: TestViewModel = hiltViewModel(it)
            TestScreen(onNavigate = {navHostController.navigate(Screen.SportClubList.route)})
        }
        composable(
            route = Screen.SignUp.route,
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(300),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = { // Анимация при выходе с экрана
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                )
            }
        ) {

            SignUpScreenRoute(
                onNavigateToMainScreen = { /*TODO*/ },
                onNavigateToSportClubsListScreen = {
                    navHostController.navigate(Screen.SportClubList.route)
                }
            )
        }
        composable(Screen.SignIn.route) {
            LoginScreenRoute(
                onNavigateToMainScreen = { /*TODO*/ },
                onNavigateToSportClubsListScreen = {
                    navHostController.navigate(
                        route = Screen.SportClubList.route
                    )
                },
                onNavigateToSignUpScreen = {
                    navHostController.navigate(
                        route = Screen.SignUp.route
                    )
                } ,
            )
        }

        composable(Screen.SportClubList.route,
            popEnterTransition = {
                slideIntoContainer(
                    animationSpec = tween(300),
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    initialOffset = { -200 }
                )
            },
            exitTransition = { // Анимация при выходе с экрана
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(400, easing = EaseOut),
                    targetOffset = { -300 }
                )
            }
        ) { navBackStackEntry ->
//            val text = entry.savedStateHandle.get<String>("my_text")
            //val viewModel: SportClubListViewModel = hiltViewModel(navBackStackEntry)

            SportsClubsListRoute(
                onNavigateToDetailSportsClubsScreen = { sportClubId ->
                    navHostController.navigate(
                        route = Screen.SelectedSportClub(sportClubId).getRouteWithArgs()
                    )
                },
                onNavigateToFiltersSportsClubsScreen = {
                    navHostController.navigate(
                        route = Screen.SportClubFilters.route
                    )
                }

            )
        }
        composable(
            route = Screen.ROUTE_SELECTED_SPORT_CLUB,
            arguments = listOf(navArgument(Screen.KEY_SPORT_CLUB) { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(400),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(400, )
                )
            }
        ) {
            SportClubInfoRoute(
                sportClubId = it.arguments?.getInt(Screen.KEY_SPORT_CLUB) ?: throw (Exception()),//todo
                onNavigateToActivitiesTableScreen = {
                    //todo
                }
            )
        }

        composable(
            route = Screen.SportClubFilters.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(300, easing = FastOutSlowInEasing),
                    initialOffset = { it }
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(400, easing = LinearEasing),
                    targetOffset = { it }
                ) + fadeOut(
                    animationSpec = tween(200, easing = FastOutLinearInEasing),
                    targetAlpha = 0f
                )
            }
        ) {

            SportClubsFiltersRoute(
                onNavigateBack = {
                    navHostController.popBackStack()
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