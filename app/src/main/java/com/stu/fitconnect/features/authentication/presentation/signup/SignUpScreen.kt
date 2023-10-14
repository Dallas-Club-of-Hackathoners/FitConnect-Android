package com.stu.fitconnect.features.authentication.presentation.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.authentication.presentation.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToMainScreen: () -> Unit,
    onNavigateToSportClubsListScreen: () -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

//    LaunchedEffect(key1 = Unit) {
//        event.invoke(LoginContract.Event.OnLogin) // входим если есть в бд
//    }

    SignUpScreen()
}


@Composable
fun SignUpScreen(

) {

}