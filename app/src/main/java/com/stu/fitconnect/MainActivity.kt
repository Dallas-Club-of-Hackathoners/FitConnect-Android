package com.stu.fitconnect

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
import com.stu.fitconnect.features.sportsclubs.domain.AppLocation
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.presentation.list.SportClubListContract
import com.stu.fitconnect.features.sportsclubs.presentation.list.SportsClubsListScreenPreview
import com.stu.fitconnect.ui.theme.FitConnectTheme
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitConnectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SportsClubsListScreenPreview(
                        SportClubListContract.State(
                            pagingSportsClubList = flowOf(
                                PagingData.from(
                                    listOf(
                                        SportClubSummary(
                                            id = 1,
                                            name = "Спортзал 1",
                                            imagesRes = listOf(0),
                                            location = AppLocation(
                                                latitude = 52.520008,
                                                longitude = 13.404954,
                                                address = "Адрес 1",
                                                city = "Город 1",
                                                metro = "Метро 1"
                                            ),
                                            score = 4.5,
                                            reviewsCount = 100,
                                            cost = 2000,
                                            isFavorite = false
                                        ),
                                        SportClubSummary(
                                            id = 2,
                                            name = "Спортзал 2",
                                            imagesRes = listOf(0),
                                            location = AppLocation(
                                                latitude = 52.520008,
                                                longitude = 13.404954,
                                                address = "Адрес 2",
                                                city = "Город 2",
                                                metro = "Метро 2"
                                            ),
                                            score = 4.0,
                                            reviewsCount = 75,
                                            cost = 1500,
                                            isFavorite = true
                                        )
                                    )
                                )
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitConnectTheme {
        Greeting("Android")
    }
}