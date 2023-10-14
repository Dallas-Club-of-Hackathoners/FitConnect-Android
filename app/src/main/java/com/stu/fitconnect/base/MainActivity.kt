package com.stu.fitconnect.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stu.fitconnect.features.authentication.presentation.login.LoginScreen
import com.stu.fitconnect.features.authentication.presentation.login.LoginScreenPreview
import com.stu.fitconnect.features.sportclubs.presentation.list.SportsClubsListScreenPreview
import com.stu.fitconnect.ui.theme.FitConnectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitConnectTheme {
                MainScreen()
            }
        }
    }
}