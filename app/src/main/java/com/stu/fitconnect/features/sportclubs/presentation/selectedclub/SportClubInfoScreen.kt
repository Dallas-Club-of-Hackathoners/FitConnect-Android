package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.Green1


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

    SportClubInfoScreen(state = state)
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SportClubInfoScreen(state: SportClubInfoContract.State) {
    val res = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/05/5a/2f/77/the-club.jpg?w=1200&h=1200&s=1"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((LocalConfiguration.current.screenHeightDp * 0.20).dp) // 15% высоты экрана
        ) {
            GlideImage(
                model = state.sportClub?.imagesRes?.get(0),
                contentDescription = "sportClubImage",
                modifier = Modifier
                    .fillMaxSize()
            )
            ClickableIcon(

                icon = Icons.Default.Favorite,
                onClick = { /*TODO*/ },
            )
        }
        // Изображение


        // Ряд с логотипом, названием и иконкой лайка
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Логотип
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "sportClubLogo",
                modifier = Modifier
                    .size(48.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = state.sportClub?.name ?: "Название клуба",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, // Устанавливаем цвет текста

                )
            )
            // Название и иконка "Лайк"
            Spacer(modifier = Modifier.width(130.dp))

        }

        Divider(
            color = Color.Gray, // Цвет разделителя
            thickness = 1.dp, // Толщина разделителя
            modifier = Modifier
                .fillMaxWidth() // Растягиваем по всей ширине
                .padding(vertical = 8.dp) // Устанавливаем вертикальные отступы
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
        ) {
            Text(text = state.sportClub?.description ?: "Описание клуба",
                style = TextStyle(
                    color = Color.White, // Устанавливаем цвет текста
                    // Остальные параметры стиля
                ))

            Spacer(modifier = Modifier.height(8.dp))

            state.sportClub?.location?.metro?.let { Text(text = it,
                style = TextStyle(
                    color = Color.White, // Устанавливаем цвет текста
                    // Остальные параметры стиля
                )) }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = state.sportClub?.location?.address ?: "Адрес клуба",
                style = TextStyle(
                    color = Color.White, // Устанавливаем цвет текста
                    // Остальные параметры стиля
                ))

            Spacer(modifier = Modifier.height(8.dp))

            // Рейтинг и количество отзывов
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Рейтинг: ${String.format("%.1f",state.sportClub?.score)}",
                    style = TextStyle(
                        color = Color.White, // Устанавливаем цвет текста
                        // Остальные параметры стиля
                    ))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Отзывов: ${state.sportClub?.reviewsCount}", color = Color.Gray)
                Spacer(modifier = Modifier.width(25.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(34.dp)
                ) {
                    Text(text = "Оставить отзыв",
                        style = TextStyle(
                            color = Color.White, // Устанавливаем цвет текста
                            // Остальные параметры стиля
                        ))
                }
            }

        }





        Divider(
            color = Color.Gray, // Цвет разделителя
            thickness = 1.dp, // Толщина разделителя
            modifier = Modifier
                .fillMaxWidth() // Растягиваем по всей ширине
                .padding(vertical = 8.dp) // Устанавливаем вертикальные отступы
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )  {

            Text(text = "Удобства",
                style = TextStyle(
                    color = Color.White, // Устанавливаем цвет текста
                    // Остальные параметры стиля
                ))

            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    IconWithText(icon = Icons.Default.Info, text ="Парковка" )
                    IconWithText(icon = Icons.Default.Info, text ="Душ" )
                    IconWithText(icon = Icons.Default.Info, text ="Кафе" )
                    IconWithText(icon = Icons.Default.Info, text ="Можно с 14 лет")
                    IconWithText(icon = Icons.Default.Info, text ="Можно с 16 лет" )

                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    IconWithText(icon = Icons.Default.Info, text ="Полотенце" )
                    IconWithText(icon = Icons.Default.Info, text ="Вода" )
                    IconWithText(icon = Icons.Default.Info, text ="Не нужна обувь" )
                    IconWithText(icon = Icons.Default.Info, text ="Dog friendly" )
                    IconWithText(icon = Icons.Default.Info, text ="Только для женщин" )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }


        Divider(
            color = Color.Gray, // Цвет разделителя
            thickness = 1.dp, // Толщина разделителя
            modifier = Modifier
                .fillMaxWidth() // Растягиваем по всей ширине
                .padding(vertical = 8.dp) // Устанавливаем вертикальные отступы
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )  {
            Text(text = "Тренеры",
                style = TextStyle(
                    color = Color.White, // Устанавливаем цвет текста
                    // Остальные параметры стиля
                ))
            Spacer(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.70).dp))
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Green1) // Иконка после текста
        }


        Divider(
            color = Color.Gray, // Цвет разделителя
            thickness = 1.dp, // Толщина разделителя
            modifier = Modifier
                .fillMaxWidth() // Растягиваем по всей ширине
                .padding(vertical = 8.dp) // Устанавливаем вертикальные отступы
        )

        Spacer(modifier = Modifier.height((LocalConfiguration.current.screenHeightDp * 0.1).dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )  {

            Button(
                onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = BackgroundColor, // Здесь задайте цвет текста
                    containerColor = Green1,                )
            ) {
                Text(text = "Записаться")
            }
            Row {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 4.dp, top = 5.dp),

                ) {
                    Text(text = "Расписание",
                        style = TextStyle(
                            color = Color.White, // Устанавливаем цвет текста
                        )
                        )
                }


                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp, top = 5.dp),

                ) {
                    Text(text = "Связаться",
                        style = TextStyle(
                            color = Color.White, // Устанавливаем цвет текста
                        )
                    )
                }
            }
        }


    }
}


//@OptIn(ExperimentalStdlibApi::class)
//@Preview
//@Composable
//fun SportClubInfoScreenPreview(
//    @PreviewParameter(SportClubPreviewProvider::class)
//    sportClubInfoState: SportClubInfoContract.State
//) {
//    Surface {
//        SportClubInfoScreen(
//            sportClub = sportClubInfoState.sportClub!!
//        )
//    }
//}

@Composable
fun ClickableIcon(icon: ImageVector, onClick: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }
    val tint = if (isClicked) Green1 else Color.Unspecified

    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier
            .padding(start = ((LocalConfiguration.current.screenWidthDp * 0.85).dp), top = 40.dp)
            .clickable {
                isClicked = !isClicked
                onClick()
            }
    )
}
@Composable
fun IconWithText(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Расстояние между иконкой и текстом
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Green1) // Отображаем иконку
        Text(text = text,
            style = TextStyle(
                color = Color.White, // Устанавливаем цвет текста
                // Остальные параметры стиля
            )) // Отображаем текст
    }
}
