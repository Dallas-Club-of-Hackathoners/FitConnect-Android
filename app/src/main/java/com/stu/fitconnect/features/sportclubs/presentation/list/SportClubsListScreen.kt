package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.stu.fitconnect.R
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.sportclubs.domain.entity.AppLocation
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.Green1
import kotlinx.coroutines.flow.collectLatest

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
                .padding(16.dp)
        ) {
            Row( verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear", tint = Color.White)
                Spacer(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.50).dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = BackgroundColor, // Здесь задайте цвет текста
                        containerColor = Green1, // Здесь задайте цвет кнопки
                    )                    ) {
                    Text(text = "3000 баллов",
                        style = TextStyle(
                            color = BackgroundColor,
                        )
                        )
                }
            }

            // Строка для ввода поиска
            OutlinedTextField(
                value = sportsClubsListState.searchText,
                onValueChange = { newText ->
                    onSearch(newText)
                },
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                label = {
                    Text(text = "Поиск спортзала")
                },
                textStyle = TextStyle(color = Color.White), // Задаем цвет текста
                shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp), // Размер иконки
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search, // Иконка поиска
                            contentDescription = null, // Описание контента (можно оставить пустым)
                        )
                    }
                }
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedButton(
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .height(27.dp)
                        .width(35.dp),
                    onClick = { /* Ваша логика при нажатии */ },
//                    shape = RoundedCornerShape(20.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(19.dp),
                        imageVector = Icons.Default.FavoriteBorder, // Пример иконки "Избранное"
                        contentDescription = null,
                        tint = Green1 // Задаем цвет иконки
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                OutlinedButton(
                    onClick = { /* Ваша логика при нажатии */ },
//                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .height(27.dp)
                        .width(35.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(19.dp),
                        imageVector = Icons.Default.Menu, // Пример иконки "Избранное"
                        contentDescription = null,
                        tint = Green1 // Задаем цвет иконки
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Рекомендуем",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                        )
                    )

                }
            }



            Spacer(modifier = Modifier.height(16.dp))

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
        Column( ) {

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
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                        color = Color.White
                    )
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
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    color = Green1))
            Text(
                text = sportClub.location.address,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    color = Color.White,
                )
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
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            color = Color.White
                        )
                    )

                    Text(
                        text = "${String.format("%.1f", sportClub.score)}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                            color = Green1
                        )
                    )
                    Text(
                        text = " (${sportClub.reviewsCount} отзывов)",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                            color = Color.Gray
                        )
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
    Surface {
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
        Text(text = text,
            color = Green1)
    }
}


