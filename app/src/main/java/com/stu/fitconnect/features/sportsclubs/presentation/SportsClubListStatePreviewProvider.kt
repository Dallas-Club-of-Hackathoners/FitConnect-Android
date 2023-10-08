package com.stu.fitconnect.features.sportsclubs.presentation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import com.stu.fitconnect.features.sportsclubs.domain.AppLocation
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.presentation.list.SportsClubListContract
import kotlinx.coroutines.flow.flowOf

class SportsClubListStatePreviewProvider : PreviewParameterProvider<SportsClubListContract.State> {
    override val values: Sequence<SportsClubListContract.State> = sequenceOf(
        SportsClubListContract.State(
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