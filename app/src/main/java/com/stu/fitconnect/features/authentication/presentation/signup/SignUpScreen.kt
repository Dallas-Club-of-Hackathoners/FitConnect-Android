package com.stu.fitconnect.features.authentication.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stu.fitconnect.R
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.authentication.domain.AuthField
import com.stu.fitconnect.composables.AuthTextField
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.ButtonColor

@Composable
fun SignUpScreenRoute(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateToMainScreen: () -> Unit,
    onNavigateToSportClubsListScreen: () -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

//    LaunchedEffect(key1 = Unit) {
//        event.invoke(LoginContract.Event.OnLogin) // входим если есть в бд
//    }
    SignUpScreen(
        signUpState = state,
        onNavigateToSportClubsListScreen = onNavigateToSportClubsListScreen,
        onSignUpDataChanged = { value, type ->
            event.invoke(SignUpContract.Event.OnSignInDataChanged(value, type))
        },
        onChangeRememberUser = { rememberUser ->
            event.invoke(SignUpContract.Event.OnChangeRememberUser(rememberUser = rememberUser))
        },
        onSignUp = {
            event.invoke(SignUpContract.Event.OnSignIn(navigateToMainScreen = {
                onNavigateToSportClubsListScreen()
            }))
        }
    )

}


@Composable
fun SignUpScreen(
    signUpState: SignUpContract.State,
    onNavigateToSportClubsListScreen: () -> Unit,
    onSignUpDataChanged: (value: String, type: AuthField) -> Unit,
    onChangeRememberUser: (rememberUser: Boolean) -> Unit,
    onSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 17.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Зарегистрироваться",
            style = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
//                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),

                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        AuthTextField(
            value = signUpState.signUpData.email,
            label = "Почта",
            onValueChange = {
                onSignUpDataChanged(it, AuthField.Email)
            },
            fieldType = AuthField.Email
        )
        Spacer(modifier = Modifier.height(15.dp))
        AuthTextField(
            value = signUpState.signUpData.password,
            label = "Пароль",
            onValueChange = {
                onSignUpDataChanged(it, AuthField.Password)
            },
            fieldType = AuthField.Password
        )
        Spacer(modifier = Modifier.height(15.dp))
        AuthTextField(
            value = signUpState.signUpData.repeatPassword,
            label = "Повтор пароля",
            onValueChange = {
                onSignUpDataChanged(it, AuthField.RepeatPassword)
            },
            fieldType = AuthField.Password
        )
        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onSignUp,
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp),
            shape = RoundedCornerShape(17.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
        ) {
            Text(
                text = "Зарегистрироваться",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.montserrat_medium))
            )
        }
    }
}