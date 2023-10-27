@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalGlideComposeApi::class
)

package com.stu.fitconnect.features.sportclubs.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.stu.fitconnect.R
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.sportclubs.domain.entity.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.ui.AppIconButton
import com.stu.fitconnect.ui.AppOutlineButton
import com.stu.fitconnect.ui.IconWithText
import com.stu.fitconnect.ui.RubleCostIcons
import com.stu.fitconnect.ui.SearchTextField
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Gray
import com.stu.fitconnect.ui.theme.Green
import kotlinx.coroutines.flow.emptyFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SportsClubsListRoute(
    viewModel: SportClubListViewModel = hiltViewModel(),
    onNavigateToDetailSportsClubsScreen: (sportClubId: Int) -> Unit,
    onNavigateToFiltersSportsClubsScreen: () -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)
    LaunchedEffect(key1 = Unit) {
        if(viewModel.state.value.pagingSportClubList == emptyFlow<PagingData<SportClubSummary>>()) {
            event.invoke(SportClubListContract.Event.OnGetSportClub)
            event.invoke(SportClubListContract.Event.OnGetSportClubFilters)
        }
    }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    Scaffold {
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

}

@Composable
fun SportsClubsListScreen(
    sportsClubsListState: SportClubListContract.State,
    onNavigateToDetailSportsClubsScreen: (sportClubId: Int) -> Unit,
    onNavigateToFiltersSportsClubsScreen: () -> Unit,
    onSearch: (searchBy: String) -> Unit,
    onApplySelectedFilters: (filters: SportClubsFiltersData) -> Unit,
    onRefresh: () -> Unit,
) {

    val stablePagingSportClubList = remember { sportsClubsListState.pagingSportClubList }
    val stableSelectedFilters = remember { sportsClubsListState.selectedFilters }
    val lazyColumnListState = rememberLazyListState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor) // BackgroundColor
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
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
//                    )
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
                    textStyle = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.height(27.dp)
                )
            }

            val sportsClubsPagingItems: LazyPagingItems<SportClubSummary> =
                sportsClubsListState.pagingSportClubList.collectAsLazyPagingItems()

            val pullRefreshState = rememberPullRefreshState(
                sportsClubsListState.refreshing,
                onRefresh = onRefresh
            )

            Box(
                Modifier.pullRefresh(pullRefreshState, enabled = !sportsClubsListState.refreshing)
            ) {

                LazyColumn(
                    state = lazyColumnListState,
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(35.dp)
                ) {
                    items(
                        sportsClubsPagingItems.itemCount,
//                        key = {
//                            sportsClubsPagingItems[it]?.id ?: null as Any
//                        },
                    ) { index ->
                        sportsClubsPagingItems[index]?.let {
                            SportsClubListCard(
                                sportClub = it,
                                onItemClick = { sportClubId ->
                                    onNavigateToDetailSportsClubsScreen(sportClubId)
                                }
                            )
                        }
                    }
                }


                PullRefreshIndicator(
                    refreshing = sportsClubsListState.refreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    backgroundColor = Green,
                )
            }
        }
    }
}

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
                model = "https://images.unsplash.com/photo-1571902943202-507ec2618e8f?auto=format&fit=crop&q=80&w=1975&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", //sportClub.imagesRes[0],
                contentDescription = "sportClubImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .height((/*LocalConfiguration.current.screenHeightDp * 0.15*/ 170).dp)
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(18.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = sportClub.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Местоположение
                    sportClub.location.metro?.let { metro ->
                        IconWithText(
                            textValue = metro,
                            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Green),
                            iconRes = R.drawable.metro
                        )
                    }
                    IconWithText(
                        textValue = sportClub.location.address,
                        iconRes = R.drawable.location
                    )


                    // Рейтинг и количество отзывов
                    Row(
                        modifier = Modifier
                            .padding(top = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row {
                            Text(
                                text = "Оценка: ",
                                style = MaterialTheme.typography.headlineSmall
                            )

                            Text(
                                text = String.format("%.1f", sportClub.score),
                                style = MaterialTheme.typography.headlineSmall.copy(color = Green)
                            )
                            Text(
                                text = " (${sportClub.reviewsCount} отзывов)",
                                style = MaterialTheme.typography.headlineSmall.copy(color = Gray)
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 6.dp)
                ) {
                    Icon(
                        imageVector = if (sportClub.isFavorite) Icons.Default.FavoriteBorder else Icons.Default.FavoriteBorder, // todo
                        contentDescription = null,
                        tint = Green,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    RubleCostIcons(
                        cost = sportClub.cost
                    )
                }
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
            cost = 2,
            category = "abc",
            isFavorite = false
        )
        SportsClubListCard(sportClub = sportClub, onItemClick = {})
    }
}
