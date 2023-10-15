package com.stu.fitconnect.features.authentication.presentation.login

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stu.fitconnect.R
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.authentication.domain.AuthField
import com.stu.fitconnect.ui.AuthTextField
import com.stu.fitconnect.ui.theme.ButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToMainScreen: () -> Unit,
    onNavigateToSportClubsListScreen: () -> Unit,
    onNavigateToSignUpScreen: () -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

//    LaunchedEffect(key1 = Unit) {
//        event.invoke(LoginContract.Event.OnLogin) // входим если есть в бд
//    }

    LoginScreen(
        loginState = state,
        onNavigateToSportClubsListScreen = onNavigateToSportClubsListScreen,
        onNavigateToSignUpScreen = onNavigateToSignUpScreen,
        onSignInDataChanged = { value, type ->
            event.invoke(LoginContract.Event.OnSignInDataChanged(value, type))
        },
        onChangeRememberUser = { rememberUser ->
            event.invoke(LoginContract.Event.OnChangeRememberUser(rememberUser = rememberUser))
        },
        onLogin = {
            event.invoke(LoginContract.Event.OnLogin(navigateToMainScreen = {
                onNavigateToSportClubsListScreen()
            }))
        }
    )
}


@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    loginState: LoginContract.State,
    onNavigateToSportClubsListScreen: () -> Unit,
    onNavigateToSignUpScreen: () -> Unit,
    onSignInDataChanged: (value: String, type: AuthField) -> Unit,
    onChangeRememberUser: (rememberUser: Boolean) -> Unit,
    onLogin: () -> Unit
) {
//    val snackbarHostState = remember { SnackbarHostState() }
//    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1D1D1D))
            .padding(horizontal = 17.dp), // BackgroundColor

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Вход",
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
            value = loginState.signInData.email,
            label = "Почта",
            onValueChange = {
                onSignInDataChanged(it, AuthField.Email)
            },
            fieldType = AuthField.Email
        )
        Spacer(modifier = Modifier.height(15.dp))
        AuthTextField(
            value = loginState.signInData.password,
            label = "Пароль",
            onValueChange = {
                onSignInDataChanged(it, AuthField.Password)
            },
            fieldType = AuthField.Password
        )
        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp),
            shape = RoundedCornerShape(17.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
        ) {
            Text(
                text = "Войти",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)))
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onNavigateToSignUpScreen,
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp),
            shape = RoundedCornerShape(17.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
        ) {
            Text(
                text = "Нет аккаунта? Зарегистрироваться",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)))
        }
//    {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null) // Иконка перед текстом
//                Spacer(modifier = Modifier.width(1.dp)) // Расстояние между иконкой и текстом
//                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null) // Иконка перед текстом
//                Spacer(modifier = Modifier.width(1.dp)) // Расстояние между иконкой и текстом
//                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null) // Иконка перед текстом
//                Spacer(modifier = Modifier.width(22.dp)) // Расстояние между иконкой и текстом
//                Text(
//                    text = "Сonnect",
//                    fontFamily = FontFamily(Font(R.font.montserrat_italic)),
//                    )
//                Spacer(modifier = Modifier.width(22.dp)) // Расстояние между иконкой и текстом
//                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null) // Иконка после текста
//                Spacer(modifier = Modifier.width(1.dp)) // Расстояние между иконкой и текстом
//                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null) // Иконка после текста
//                Spacer(modifier = Modifier.width(1.dp)) // Расстояние между текстом и второй иконкой
//                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null) // Иконка после текста
//            }
//
//        }
//
//    }
//    SnackbarHost(
//        hostState = snackbarHostState,
//        modifier = Modifier
//            .navigationBarsPadding()
//            .statusBarsPadding()
//    )

    }
}
//@Composable
//fun NextScreenButton(onClick: () -> Unit) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(text = "Далее")
//    }
//}


fun isValidEmail(email: String): Boolean {

    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: String): Boolean {
    return password.isNotEmpty() // Добавьте здесь свою логику проверки пароля
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        loginState = LoginContract.State(),
        onNavigateToSportClubsListScreen = {},
        onNavigateToSignUpScreen = {},
        onSignInDataChanged = { _, _ -> },
        onChangeRememberUser = {},
        onLogin = {},
    )
}
