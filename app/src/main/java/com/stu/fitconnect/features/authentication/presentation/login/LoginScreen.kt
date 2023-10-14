package com.stu.fitconnect.features.authentication.presentation.login


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stu.fitconnect.R
import com.stu.fitconnect.base.use
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToMainScreen: () -> Unit,
    onNavigateToSportClubsListScreen: () -> Unit,
    onNavigateToSignUpScreen: (email:String) -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

//    LaunchedEffect(key1 = Unit) {
//        event.invoke(LoginContract.Event.OnLogin) // входим если есть в бд
//    }

    LoginScreen(
        loginState = state,
        onNavigateToMainScreen = onNavigateToMainScreen,
        onNavigateToSportClubsListScreen = onNavigateToSportClubsListScreen,
        onNavigateToSignUpScreen = onNavigateToSignUpScreen,
        OnEmailChanged = { email ->
            event.invoke(LoginContract.Event.OnEmailChanged(email = email))
        },
        OnPasswordChanged = { password ->
            event.invoke(LoginContract.Event.OnPasswordChanged(password = password))
        },
        OnChangeRememberUser = { rememberUser ->
            event.invoke(LoginContract.Event.OnChangeRememberUser(rememberUser = rememberUser))
        },
        OnLogin = {
            event.invoke(LoginContract.Event.OnLogin)
        }
    )
}



@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    loginState: LoginContract.State,
    onNavigateToMainScreen: () -> Unit,
    onNavigateToSportClubsListScreen: () -> Unit,
    onNavigateToSignUpScreen: (email:String) -> Unit,
    OnEmailChanged: (email: String) -> Unit,
    OnPasswordChanged: (password: String) -> Unit,
    OnChangeRememberUser: (rememberUser: Boolean) -> Unit,
    OnLogin: () -> Unit,


    ) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1D1D1D)), // BackgroundColor
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
        Spacer(modifier = Modifier.height(10.dp))

        var email by remember { mutableStateOf("") }
        var isValidEmail by remember { mutableStateOf(true) }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = email,
            onValueChange = {
                email = it
                isValidEmail = isValidEmail(it)
                OnEmailChanged (it)
            },
            label = { Text("Почта") },
            textStyle = TextStyle(color = if (isValidEmail) Color.Green else Color.Red),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = if (isValidEmail) Color.Black else Color.Red,
                unfocusedIndicatorColor = if (isValidEmail) Color.Black else Color.Red,
                textColor = if (isValidEmail) Color.Green else Color.Red,
            ),
            shape = RoundedCornerShape(20.dp),
            trailingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = email)
            },

        )



        var password by rememberSaveable { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = password,
            onValueChange = {
                password = it
                OnPasswordChanged (it)
                            },
            label = { Text("Пароль") },
            trailingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Warning else Icons.Default.Lock,
                        contentDescription = if (isPasswordVisible) "Скрыть пароль" else "Показать пароль"
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                textColor = Color.White,
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),

            )



        Button(
            onClick = {
                if (isValidEmail(email) && isValidPassword(password)) {
                    // Выполнить действие, если почта и пароль валидны
                    // todo крч надо сделать проверку на существование юзера, надеюсь она включена в юз кейс
                    OnLogin
                    onNavigateToSportClubsListScreen
                } else {
                      coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Проверьте валидность данных",
                            actionLabel = "Закрыть"
                        )
                        delay(3000) // Time in milliseconds
                        snackbarHostState.currentSnackbarData?.dismiss() //пока не работает
                    }
                }

                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Green, // Здесь задайте цвет текста
                containerColor = Color(0xFF383838), // Здесь задайте цвет кнопки
            )

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null) // Иконка перед текстом
                Spacer(modifier = Modifier.width(1.dp)) // Расстояние между иконкой и текстом
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null) // Иконка перед текстом
                Spacer(modifier = Modifier.width(1.dp)) // Расстояние между иконкой и текстом
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null) // Иконка перед текстом
                Spacer(modifier = Modifier.width(22.dp)) // Расстояние между иконкой и текстом
                Text(
                    text = "Сonnect",
                    fontFamily = FontFamily(Font(R.font.montserrat_italic)),
                    )
                Spacer(modifier = Modifier.width(22.dp)) // Расстояние между иконкой и текстом
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null) // Иконка после текста
                Spacer(modifier = Modifier.width(1.dp)) // Расстояние между иконкой и текстом
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null) // Иконка после текста
                Spacer(modifier = Modifier.width(1.dp)) // Расстояние между текстом и второй иконкой
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null) // Иконка после текста
            }

        }

    }
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding()
    )

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
        onNavigateToMainScreen = {},
        onNavigateToSportClubsListScreen = {},
        onNavigateToSignUpScreen = {},
        OnEmailChanged = {},
        OnPasswordChanged = {},
        OnChangeRememberUser = {},
        OnLogin = {},
    )
}