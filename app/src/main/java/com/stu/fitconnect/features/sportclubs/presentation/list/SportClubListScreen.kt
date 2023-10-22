package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.sportclubs.domain.entity.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.ui.AppIconButton
import com.stu.fitconnect.ui.AppOutlineButton
import com.stu.fitconnect.ui.SearchTextField
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Gray
import com.stu.fitconnect.ui.theme.Green
import com.stu.fitconnect.ui.theme.Green1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsClubsListRoute(
    viewModel: SportClubListViewModel = hiltViewModel(),
    onNavigateToDetailSportsClubsScreen: (sportClubId: Int) -> Unit,
    onNavigateToFiltersSportsClubsScreen: () -> Unit,
) {

    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        event.invoke(SportClubListContract.Event.OnGetSportClub)
        event.invoke(SportClubListContract.Event.OnGetSportClubFilters)
    }

    SportsClubsListScreen(
        sportsClubsListState = state,
        onNavigateToDetailSportsClubsScreen = onNavigateToDetailSportsClubsScreen,
        onNavigateToFiltersSportsClubsScreen = onNavigateToFiltersSportsClubsScreen,
        onSearch = { searchBy ->
            event.invoke(SportClubListContract.Event.OnSearchSportClub(searchBy = searchBy))
        },
        onApplySelectedFilters = { filters ->
            event.invoke(SportClubListContract.Event.OnApplySelectedFilters(sportsClubsFilters = filters))
        },
        onRefresh = {
            event.invoke(SportClubListContract.Event.OnRefresh)
        }
    )
}


@ExperimentalMaterial3Api
@Composable
fun SportsClubsListScreen(
    sportsClubsListState: SportClubListContract.State,
    onNavigateToDetailSportsClubsScreen: (sportClubId: Int) -> Unit,
    onNavigateToFiltersSportsClubsScreen: () -> Unit,
    onSearch: (searchBy: String) -> Unit,
    onApplySelectedFilters: (filters: SportClubsFiltersData) -> Unit,
    onRefresh: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor) // BackgroundColor
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {

//            перенести логику в другое место - вне скринов или на мейн скрин?
//            Row( verticalAlignment = Alignment.CenterVertically) {
//                Spacer(modifier = Modifier.width(10.dp))
//                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear", tint = Color.White)
//                Spacer(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.50).dp))
//                Button(
//                    onClick = { /*TODO*/ },
//                    colors = ButtonDefaults.buttonColors(
//                        contentColor = BackgroundColor, // Здесь задайте цвет текста
//                        containerColor = Green1, // Здесь задайте цвет кнопки
//                    )                    ) {
//                    Text(text = "3000 баллов",
//                        style = TextStyle(
//                            color = BackgroundColor,
//                        )
//                        )
//                }
//            }

            SearchTextField(
                value = sportsClubsListState.searchText,
                onValueChange = { newText ->
                    onSearch(newText)
                },
                label = "Поиск спортзала"
            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {

                AppIconButton(
                    onClick = { /*TODO*/ },
                    icon = Icons.Default.FavoriteBorder
                )

                AppIconButton(
                    onClick = { /*TODO*/ },
                    icon = Icons.Default.Menu
                )

                AppOutlineButton(
                    onClick = { /*TODO*/ },
                    textValue = "Рекомендуем",
                    textStile = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.height(27.dp)
                )
            }

            // Список спортзалов
            val sportsClubsPagingItems: LazyPagingItems<SportClubSummary> =
                sportsClubsListState.pagingSportClubList.collectAsLazyPagingItems()


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(sportsClubsPagingItems.itemCount) { index ->
                    SportsClubListCard(
                        sportClub = sportsClubsPagingItems[index]!!,
                        onItemClick = { sportClubId ->
                            // Передаем sportClubId при клике на элемент списка
                            onNavigateToDetailSportsClubsScreen(sportClubId)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SportsClubListCard(
    sportClub: SportClubSummary,
    onItemClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(sportClub.id) },
    ) {
        Column {
            GlideImage(
                model = sportClub.imagesUrls[0], //sportClub.imagesRes[0],
                contentDescription = "sportClubImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .height((LocalConfiguration.current.screenHeightDp * 0.15).dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = sportClub.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Icon(
                    imageVector = if (sportClub.isFavorite) Icons.Default.FavoriteBorder else Icons.Default.FavoriteBorder, // todo
                    contentDescription = null,
                    tint = Green1,
                    modifier = Modifier.size(16.dp)
                )
            }

            // Местоположение
            Text(
                text = sportClub.location.metro ?: "Станция метро",
                style = MaterialTheme.typography.bodyMedium.copy(color = Green)
            )
            Text(
                text = sportClub.location.address,
                style = MaterialTheme.typography.bodyMedium
            )

            // Рейтинг и количество отзывов
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row {
                    Text(
                        text = "Оценка: ",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = "${String.format("%.1f", sportClub.score)}",
                        style = MaterialTheme.typography.headlineSmall.copy(color = Green)
                    )
                    Text(
                        text = " (${sportClub.reviewsCount} отзывов)",
                        style = MaterialTheme.typography.headlineSmall.copy(color = Gray)
                    )
                }
                //cost icon
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SportsClubsListScreenPreview(
    @PreviewParameter(SportClubListStatePreviewProvider::class)
    sportsClubsListState: SportClubListContract.State,
) {
    FitConnectTheme {
        SportsClubsListScreen(
            sportsClubsListState = sportsClubsListState,
            onNavigateToDetailSportsClubsScreen = {},
            onNavigateToFiltersSportsClubsScreen = {},
            onSearch = {},
            onApplySelectedFilters = {},
            onRefresh = {}
        )
    }
}

@Preview
@Composable
fun SportsClubsItemListScreenPreview() {
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
            cost = "2000",
            category = "abc",
            isFavorite = false
        )
        SportsClubListCard(sportClub = sportClub, onItemClick = {})
    }
}

@Composable
fun IconWithText(icon: ImageVector, text: String) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Green1
            )
            Text(
                text = text,
                color = Green1
            )
        }
}