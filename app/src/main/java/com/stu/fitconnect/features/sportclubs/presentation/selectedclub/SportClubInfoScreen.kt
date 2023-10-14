package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.stu.fitconnect.base.navigation.Screen
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.sportclubs.presentation.list.SportClubListViewModel

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun SportClubInfoRoute(
    sportClubId: Int,
    viewModel: SportClubListViewModel = hiltViewModel(), //todo change to self viewModel
    onNavigateToActivitiesTableScreen: (activityId: Int) -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
//        event(SportClubListContract.Event.OnGetSportClub(sportClubId))
    }

    SportClubInfoScreen(


    )
}


@Composable
fun SportClubInfoScreen(

) {
}