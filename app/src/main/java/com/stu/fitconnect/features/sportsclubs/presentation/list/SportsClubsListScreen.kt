package com.stu.fitconnect.features.sportsclubs.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportsclubs.domain.SortType
import com.stu.fitconnect.features.sportsclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import com.stu.fitconnect.features.sportsclubs.presentation.SportsClubListStatePreviewProvider


@ExperimentalMaterial3Api
@Composable
fun SportsClubsListScreen(
    sportsClubsListState: SportsClubListContract.State,
    onNavigateToDetailSportsClubsScreen: (sportClubId: Int) -> Unit,
    onNavigateToFiltersSportsClubsScreen: () -> Unit,
    onSearch: (searchBy: String) -> Unit,
    onSortSelected: (sortType: SortType) -> Unit,
    onApplySelectedFilters: (filters: SportsClubsFilters) -> Unit,
    onRefresh: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Строка для ввода поиска
        OutlinedTextField(
            value = sportsClubsListState.searchText,
            onValueChange = { newText ->
                onSearch(newText)
            },
            label = { Text("Поиск спортзала") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для сортировки
        Button(
            onClick = {
                // Открываем экран сортировки
                onNavigateToFiltersSportsClubsScreen()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text("Сортировать")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для фильтров
        Button(
            onClick = {
                // Открываем экран фильтров
                onNavigateToFiltersSportsClubsScreen()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text("Фильтры")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Список спортзалов
        val sportsClubsPagingItems: LazyPagingItems<SportClubSummary> =
            sportsClubsListState.pagingSportsClubList.collectAsLazyPagingItems()


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
            // Изображения спортзала (первое изображение)
            Image(
                painter = painterResource(id = sportClub.imagesRes.first()),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

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
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_favorite),
//                        contentDescription = null,
//                        tint = Color.Red,
//                        modifier = Modifier.size(16.dp)
//                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MarketListScreenPreview(
    @PreviewParameter(SportsClubListStatePreviewProvider::class)
    sportsClubsListState: SportsClubListContract.State,
) {
    Surface {
        SportsClubsListScreen(
            sportsClubsListState = sportsClubsListState,
            onNavigateToDetailSportsClubsScreen = {},
            onNavigateToFiltersSportsClubsScreen = { },
            onSortSelected = {},
            onApplySelectedFilters = {},
            onRefresh = {},
            onSearch = {}
        )
    }
}

data class SportsClub(val name: String, val address: String, val imageResId: Int)