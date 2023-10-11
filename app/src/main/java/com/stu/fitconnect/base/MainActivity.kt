package com.stu.fitconnect.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.presentation.list.SportClubListContract
import com.stu.fitconnect.features.sportclubs.presentation.list.SportsClubsListScreenPreview
import com.stu.fitconnect.ui.theme.FitConnectTheme
import kotlinx.coroutines.flow.flowOf

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