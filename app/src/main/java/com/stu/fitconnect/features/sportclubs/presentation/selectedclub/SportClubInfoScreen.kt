package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.sportclubs.domain.SportClub

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun SportClubInfoRoute(
    sportClubId: Int,
    viewModel: SportClubInfoViewModel = hiltViewModel(),
    onNavigateToActivitiesTableScreen: (activityId: Int) -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        event(SportClubInfoContract.Event.OnGetSportClub(sportClubId))
    }

    state.sportClub?.let {
        SportClubInfoScreen(
            it

    )
    }
}


@Composable
fun SportClubInfoScreen(sportClub: SportClub) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Изображения спортзала (первое изображение)
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

            // Цена и категория
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Цена: ${sportClub.cost}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Категория: ${sportClub.category}",
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            // Описание
            Text(
                text = sportClub.description,
                style = TextStyle(fontSize = 14.sp)
            )

            // Дополнительные удобства
            sportClub.facilities.forEach {
                Text(
                    text = it,
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            // Контакты
            Text(
                text = "Контакты администратора: ${sportClub.contacts}",
                style = TextStyle(fontSize = 14.sp)
            )

            // Иконка "Избранное"
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
