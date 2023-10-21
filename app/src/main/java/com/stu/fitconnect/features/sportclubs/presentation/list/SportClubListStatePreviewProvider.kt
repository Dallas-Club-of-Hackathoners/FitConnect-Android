package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.entity.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
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
                            imagesUrls = listOf(""),
                            location = AppLocation(
                                latitude = 52.520008,
                                longitude = 13.404954,
                                address = "Адрес 1",
                                city = "Город 1",
                                metro = "Метро 1"
                            ),
                            score = 4.5,
                            reviewsCount = 100,
                            cost = "2000",
                            category = "abc",
                            isFavorite = false
                        ),
                        SportClubSummary(
                            id = 2,
                            name = "Спортзал 2",
                            imagesUrls = listOf("https://6amcity.brightspotcdn.com/dims4/default/e2ff4c8/2147483647/strip/true/crop/1128x635+0+52/resize/1000x563!/format/webp/quality/90/?url=https%3A%2F%2Fk1-prod-sixam-city.s3.us-east-2.amazonaws.com%2Fbrightspot%2F04%2Fd1%2F6ce5844c4db3a6b07cddc0e74e12%2Fscreen-shot-2023-04-05-at-3-18-43-pm.png"),
                            location = AppLocation(
                                latitude = 52.520008,
                                longitude = 13.404954,
                                address = "Адрес 2",
                                city = "Город 2",
                                metro = "Метро 2"
                            ),
                            score = 4.0,
                            reviewsCount = 75,
                            cost = "1500",
                            category = "ccc",
                            isFavorite = true
                        )
                    )
                )
            )
        )
    )
}