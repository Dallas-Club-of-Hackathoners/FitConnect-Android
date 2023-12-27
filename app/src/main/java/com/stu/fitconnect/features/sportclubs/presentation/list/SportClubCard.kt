package com.stu.fitconnect.features.sportclubs.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stu.fitconnect.R
import com.stu.fitconnect.composables.IconWithText
import com.stu.fitconnect.composables.RubleCostIcons
import com.stu.fitconnect.features.sportclubs.domain.entity.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.presentation.selectedclub.ImagePager
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Gray
import com.stu.fitconnect.ui.theme.Green
import kotlinx.collections.immutable.toImmutableList

@SuppressLint("ResourceType")
@Composable
fun SportClubCard(
    sportClub: SportClubSummary,
    onCardClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick(sportClub.id) },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            ImagePager(images = sportClub.imagesUrls.take(5).toImmutableList(), imageHeight = 200.dp)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = sportClub.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    sportClub.location.metro?.let { metro ->
                        IconWithText(
                            textValue = metro,
                            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Green),
                            iconRes = R.drawable.metro
                        )
                    }
                    IconWithText(
                        textValue = sportClub.location.address ,
                        iconRes = R.drawable.location
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.grade_with_colon),
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            text = String.format("%.1f", sportClub.score),
                            style = MaterialTheme.typography.headlineSmall.copy(color = Green)
                        )
                        Text(
                            text = stringResource(R.string.reviews_count, sportClub.reviewsCount),
                            style = MaterialTheme.typography.headlineSmall.copy(color = Gray)
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(50.dp)
                        .padding(6.dp)
                ) {
                    Icon(
                        imageVector =
                        if (sportClub.isFavorite) Icons.Default.FavoriteBorder
                        else Icons.Default.FavoriteBorder, // todo
                        contentDescription = null,
                        tint = Green,
                        modifier = Modifier.size(20.dp)
                    )
                    RubleCostIcons(
                        cost = sportClub.cost
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SportClubCardPreview() {
    FitConnectTheme {
        val sportClub = SportClubSummary(
            id = 1,
            name = "Flex Balance",
            imagesUrls = listOf(""),
            location = AppLocation(
                latitude = 52.520008,
                longitude = 13.404954,
                address = "набережная реки Смоленки, дом 9",
                city = "Город 1",
                metro = "Василеостровская"
            ),
            score = 4.565,
            reviewsCount = 100,
            cost = 2,
            category = "abc",
            isFavorite = false
        )
        SportClubCard(sportClub = sportClub, onCardClick = {})
    }
}