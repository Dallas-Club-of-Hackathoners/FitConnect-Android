@file:OptIn(ExperimentalFoundationApi::class)

package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.stu.fitconnect.R
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.sportclubs.domain.entity.AmenityWithAvailable
import com.stu.fitconnect.composables.AppOutlineButton
import com.stu.fitconnect.composables.IconWithText
import com.stu.fitconnect.composables.RubleCostIcons
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.Black
import com.stu.fitconnect.ui.theme.DimGreen
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Gray
import com.stu.fitconnect.ui.theme.Green
import com.stu.fitconnect.ui.theme.TextGray
import com.stu.fitconnect.ui.theme.White
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SportClubInfoRoute(
    sportClubId: Int,
    viewModel: SportClubInfoViewModel = hiltViewModel(),
    onNavigateToActivitiesTableScreen: (activityId: Int) -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        if(state.sportClub == null)
            event(SportClubInfoContract.Event.OnGetSportClub(sportClubId))
    }

    Surface {
        SportClubInfoScreen(
            state = state
        )
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun SportClubInfoScreen(
    state: SportClubInfoContract.State
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((/*LocalConfiguration.current.screenHeightDp * 0.20*/250).dp) // 15% высоты экрана
            ) {
                ImagePager(images = state.sportClub?.imagesUrls?.toImmutableList() ?: emptyList<String>().toImmutableList(), 250.dp)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ClickableIcon(
                        icon = Icons.Default.Favorite,
                        onClick = { /*TODO*/ },
                    )
                }
//                    GlideImage(
//                        model = res, //sportClub.imagesRes[0],
//                        contentDescription = "sportClubImage",
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        contentScale = ContentScale.FillWidth
//                    )
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        ClickableIcon(
//                            icon = Icons.Default.Favorite,
//                            onClick = { /*TODO*/ },
//                        )
//                        ClickableIcon(
//                                icon = Icons.Default.Close,
//                        onClick = { /*TODO*/ },
//                        )
//                    }

            }

            // clubs name, category, logo and cost
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                ) {
                    Image(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "sportClubLogo",
                        modifier = Modifier
                            .size(60.dp)
                    )
                    Column {
                        Text(
                            text = state.sportClub?.name ?: "",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Фитнесс-клуб",
                            style = MaterialTheme.typography.headlineMedium
                        )

                    }
                }
                RubleCostIcons(cost = state.sportClub?.cost ?: 1, iconSize = 30.dp)
            }

            AppDivider()

            //description, location, score
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
            ) {
                Text(
                    text = "Описание",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = state.sportClub?.description ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                state.sportClub?.location?.metro?.let { metro ->
                    IconWithText(
                        textValue = metro,
                        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Green),
                        iconRes = R.drawable.metro
                    )
                }
                IconWithText(
                    textValue = state.sportClub?.location?.address ?: "",
                    iconRes = R.drawable.location
                )

                // rating and score
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Оценка: ",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            text = String.format("%.1f", state.sportClub?.score),
                            style = MaterialTheme.typography.headlineSmall.copy(color = Green)
                        )
                        Text(
                            text = " (${state.sportClub?.reviewsCount} отзывов) ",
                            style = MaterialTheme.typography.headlineSmall.copy(color = Gray)
                        )
                        Icon(
                            painter = painterResource(R.drawable.arrow_right),
                            modifier = Modifier
                                .height(15.dp),
                            contentDescription = null,
                            tint = White
                        )
                    }

                    AppOutlineButton(
                        onClick = { /*TODO*/ },
                        textValue = "Написать отзыв",
                        textStyle = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                            .padding(start = 14.dp)
                    )
                }
            }

            AppDivider()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {

                Text(
                    text = "Удобства",
                    style = MaterialTheme.typography.headlineMedium
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(35.dp)
                ) {
                    if (state.sportClub != null) { // todo
                        val amenitiesSize = state.sportClub.amenities.size
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            repeat(amenitiesSize - amenitiesSize / 2) { column ->
                                AmenityIconWithText(state.sportClub.amenities[column])
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            repeat(amenitiesSize / 2) { column ->
                                AmenityIconWithText(state.sportClub.amenities[column + amenitiesSize / 2])
                            }
                        }
                    }
                }
            }

            AppDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Тренеры",
                    style = MaterialTheme.typography.headlineMedium
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Green
                )
            }
            AppDivider()
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = BackgroundColor,
                    containerColor = Green,
                )
            ) {
                Text(
                    text = "Записаться",
                    style = MaterialTheme.typography.labelMedium.copy(color = Black)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                AppOutlineButton(
                    onClick = { /*TODO*/ },
                    textValue = "Как добраться",
                    textStyle = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)// todo 33
                        .weight(1f)
                )
                AppOutlineButton(
                    onClick = { /*TODO*/ },
                    textValue = "Связаться",
                    textStyle = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)// todo 33
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
fun AppDivider() {
    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ClickableIcon(icon: ImageVector, onClick: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }
    val tint = if (isClicked) Green else Color.Unspecified

    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier
            .padding(
                start = ((LocalConfiguration.current.screenWidthDp * 0.85).dp),
                top = 40.dp
            )
            .clickable {
                isClicked = !isClicked
                onClick()
            }
    )
}

@Composable
fun AmenityIconWithText(amenity: AmenityWithAvailable) {
    IconWithText(
        iconRes = LocalContext.current.resources.getIdentifier(
            amenity.iconRes,
            "drawable",
            LocalContext.current.packageName
        ),
        textValue = amenity.name,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color =
                if (amenity.available) White
                else TextGray
        ),
        textDecoration =
            if (amenity.available) null
            else TextDecoration.LineThrough,
        iconColor =
            if (amenity.available) Green
            else DimGreen
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(
    images: ImmutableList<String>,
    imageHeight: Dp,
) {
    val imagePagerState = rememberPagerState { images.size }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageHeight)
    ) {
        HorizontalPager(state = imagePagerState) { page ->
                AsyncImage(
                    model = images[page],
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(18.dp)),
                    contentScale = ContentScale.Crop,
                )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(imagePagerState.pageCount) { iteration ->
                val color = if (imagePagerState.currentPage == iteration) Green else Color.DarkGray
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun SportClubInfoScreenPreview(
    @PreviewParameter(SportClubInfoPreviewProvider::class)
    sportClubInfoState: SportClubInfoContract.State
) {
    FitConnectTheme {
        SportClubInfoScreen(
            sportClubInfoState
        )
    }
}
