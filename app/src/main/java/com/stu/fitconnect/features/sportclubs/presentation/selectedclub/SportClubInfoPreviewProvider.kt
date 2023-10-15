package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.features.sportclubs.domain.SportClubAdmin
import kotlinx.coroutines.flow.flowOf

class SportClubPreviewProvider : PreviewParameterProvider<SportClubInfoContract.State> {

    override val values: Sequence<SportClubInfoContract.State> = sequenceOf(
        SportClubInfoContract.State(
            sportClub = SportClub(
            id = 1,
            name = "Спортзал 1",
            imagesRes = listOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/05/5a/2f/77/the-club.jpg?w=1200&h=1200&s=1",
                "https://p2.zoon.ru/preview/qopEPmsUrWr_7X7bEtoxfg/640x427x85/1/0/9/original_5cb555f4142d8b39b65cc7bb_5df73f944b2c1.jpg"),
            location = AppLocation(
                latitude = 52.520008,
                longitude = 13.404954,
                address = "Адрес 1",
                city = "Город 1",
                metro = "Метро 1"
            ),
            score = 4.5,
            contacts = SportClubAdmin(
                id = 1,
                name = "Админ 1",
                phone = "123-456-789"
            ),
            description = "Описание спортзала 1",
            facilities = listOf("Facility 1", "Facility 2"),
            reviewsCount = 100,
            cost = "2000",
            category = "abc",
            isFavorite = false
        )
        )
    )
}
