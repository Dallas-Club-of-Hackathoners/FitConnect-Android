package com.stu.fitconnect.features.sportclubs.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.stu.fitconnect.features.sportclubs.domain.Filter
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import com.stu.fitconnect.base.use

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
        onApplySingleFilter = { filter ->
            event.invoke(SportClubListContract.Event.OnApplySingleFilter(filter = filter))
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
    onApplySingleFilter: (filter: Filter) -> Unit,
    onApplySelectedFilters: (filters: SportClubsFiltersData) -> Unit,
    onRefresh: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1D1D1D)) // BackgroundColor
            .padding(16.dp)
    ) {
        // Строка для ввода поиска
        OutlinedTextField(
            value = sportsClubsListState.searchText,
            onValueChange = { newText ->
                onSearch(newText)
            },
            label = {
                Text(text = "Поиск спортзала")
                TextStyle(color = Color.White)
            },
            textStyle = TextStyle(color = Color.White), // Задаем цвет текста
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(25.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, // Иконка поиска
                    contentDescription = null, // Описание контента (можно оставить пустым)
                    modifier = Modifier.padding(8.dp) // Отступ от текста
                )
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка для сортировки
            Button(
                onClick = {
                    // Открываем экран фильтров
                    onNavigateToFiltersSportsClubsScreen()
                },
                modifier = Modifier
                    .weight(1f) // Занимает доступное пространство равномерно с другой кнопкой
                    .padding(4.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White, // Здесь задайте цвет текста
                    containerColor = Color.Green, // Здесь задайте цвет кнопки
                )
            ) {
                Text("Фильтры")
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Кнопка для сортировки
            Button(
                onClick = {
                    // Открываем экран сортировки
                    onNavigateToFiltersSportsClubsScreen()
                },
                modifier = Modifier
                    .weight(1f) // Занимает доступное пространство равномерно с другой кнопкой
                    .padding(4.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White, // Здесь задайте цвет текста
                    containerColor = Color.Green, // Здесь задайте цвет кнопки
                )
            ) {
                Text("Сортировать")
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        // Список спортзалов
        val sportsClubsPagingItems: LazyPagingItems<SportClubSummary> =
            sportsClubsListState.pagingSportClubList.collectAsLazyPagingItems()


        LazyColumn(
            modifier = Modifier.fillMaxSize()
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

@Composable
fun SportsClubListCard(
    sportClub: SportClubSummary,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(sportClub.id) },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Изображения спортзала
//            ImagePager(
//                images = sportClub.imagesRes,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(120.dp)
//                    .clip(shape = RoundedCornerShape(8.dp))
//            )

            Spacer(modifier = Modifier.height(8.dp))

            // Название спортзала
            Text(
                text = sportClub.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // Местоположение
            Text(
                text = sportClub.location.address,
                style = TextStyle(fontSize = 14.sp)
            )

            // Рейтинг и количество отзывов
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sportClub.score.toString(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = " (${sportClub.reviewsCount} отзывов)",
                    style = TextStyle(fontSize = 12.sp, color = Color.Gray)
                )
            }

            // Цена и иконка "Избранное"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Цена: ${sportClub.cost} руб.",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                if (sportClub.isFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
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
    Surface {
        SportsClubsListScreen(
            sportsClubsListState = sportsClubsListState,
            onNavigateToDetailSportsClubsScreen = {},
            onNavigateToFiltersSportsClubsScreen = {},
            onSearch = {},
            onApplySingleFilter = {},
            onApplySelectedFilters = {},
            onRefresh = {}
        )
    }
}


