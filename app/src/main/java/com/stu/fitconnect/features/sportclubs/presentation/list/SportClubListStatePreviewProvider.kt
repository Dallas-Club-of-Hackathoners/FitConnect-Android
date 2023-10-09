package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.presentation.list.SportClubListContract
import kotlinx.coroutines.flow.flowOf

class SportClubListStatePreviewProvider : PreviewParameterProvider<SportClubListContract.State> {

    override val values: Sequence<SportClubListContract.State> = sequenceOf(
        SportClubListContract.State(
            pagingSportClubList = flowOf(
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