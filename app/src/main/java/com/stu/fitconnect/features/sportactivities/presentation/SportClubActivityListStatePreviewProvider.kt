package com.stu.fitconnect.features.sportactivities.presentation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import com.stu.fitconnect.features.sportactivities.domain.SportClubActivitySummary
import com.stu.fitconnect.features.sportactivities.presentation.list.SportClubActivityListContract
import kotlinx.coroutines.flow.flowOf
import java.util.Date

class SportClubActivityListStatePreviewProvider: PreviewParameterProvider<SportClubActivityListContract.State> {
    override val values: Sequence<SportClubActivityListContract.State> = sequenceOf(
        SportClubActivityListContract.State(
            pagingSportsClubActivityList = flowOf(
                PagingData.from(
                    listOf(
                        SportClubActivitySummary(
                            id = 1,
                            date = "18:00",
                            name = "Activity 1",
                            imagesRes = listOf(0, 1, 2),
                            score = 4.2,
                            cost = 1500
                        ),
                        SportClubActivitySummary(
                            id = 2,
                            date = "19:00",
                            name = "Activity 2",
                            imagesRes = listOf(3, 4, 5),
                            score = 3.8,
                            cost = 1800
                        )
                    )
                )
            )
        )
    )


}