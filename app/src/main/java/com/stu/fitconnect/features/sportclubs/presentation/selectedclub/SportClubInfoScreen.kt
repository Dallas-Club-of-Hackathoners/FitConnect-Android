package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import androidx.browser.customtabs.CustomTabsClient.getPackageName
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.stu.fitconnect.R
import com.stu.fitconnect.base.use
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.FitConnectTheme
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

    SportClubInfoScreen(
        state = state
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SportClubInfoScreen(
    state: SportClubInfoContract.State
) {
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
                model = res, //sportClub.imagesRes[0],
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
                )
            )

            state.sportClub?.amenities?.forEach {

            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val amenitiesSize = state.sportClub?.amenities?.size ?: 0
                Column {
                    repeat(amenitiesSize / 2 + 1) { column ->
                        val amenity = state.sportClub?.amenities?.get(column + 1)
                        IconWithText(
                            iconRes = LocalContext.current.resources.getIdentifier(amenity?.iconRes, "drawable", LocalContext.current.packageName),
                            text = "Парковка")
                    }
                }
                Column {
                    repeat(amenitiesSize - amenitiesSize / 2 - 1) {column ->
                        val amenity = state.sportClub?.amenities?.get(column + 1)
                        IconWithText(iconRes = R.drawable.age14, text = "Парковка")
                    }
                }
            }

//                IconWithText(iconRes = R.drawable.age14, text = "Парковка"), // todo
//                IconWithText(iconRes = R.drawable.age14, text = "Душ"), // todo
//                IconWithText(iconRes = R.drawable.cafe, text = "Кафе"),
//                IconWithText(iconRes = R.drawable.age14, text = "Можно с 14 лет"),
//                IconWithText(iconRes = R.drawable.age16, text = "Можно с 16 лет"),
//                IconWithText(iconRes = R.drawable.towel, text = "Полотенце"),
//                IconWithText(iconRes = R.drawable.bottle, text = "Вода"),
//                IconWithText(iconRes = R.drawable.cross, text = "Не нужна обувь"),
//                IconWithText(iconRes = R.drawable.pets, text = "Dog friendly"),
//                IconWithText(iconRes = R.drawable.men_woman, text = "Только для женщин")
//            )
//            Row {
//                Column(
//                    modifier = Modifier.weight(1f)
//                ) {
//                    Spacer(modifier = Modifier.height(8.dp))
//                    IconWithText(iconRes = R.drawable.age14, text ="Парковка" )
//                    IconWithText(iconRes = R.drawable.age14, text ="Душ" )
//                    IconWithText(iconRes = R.drawable.age14, text ="Кафе" )
//                    IconWithText(iconRes = R.drawable.age14, text ="Можно с 14 лет")
//                    IconWithText(iconRes = R.drawable.age14, text ="Можно с 16 лет")
//                }
//                Column(
//                    modifier = Modifier.weight(1f)
//                ) {
//                    Spacer(modifier = Modifier.height(8.dp))
//                    IconWithText(iconRes = R.drawable.age14, text ="Полотенце" )
//                    IconWithText(iconRes = R.drawable.age14, text ="Вода" )
//                    IconWithText(iconRes = R.drawable.age14, text ="Не нужна обувь" )
//                    IconWithText(iconRes = R.drawable.age14, text ="Dog friendly" )
//                    IconWithText(iconRes = R.drawable.age14, text ="Только для женщин" )
//                }
//            }
//            Spacer(modifier = Modifier.height(10.dp))
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


@Preview
@Composable
fun SportClubInfoScreenPreview(
    @PreviewParameter(SportClubPreviewProvider::class)
    sportClubInfoState: SportClubInfoContract.State
) {
    FitConnectTheme {
        SportClubInfoScreen(
            sportClubInfoState
        )
    }
}

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
fun IconWithText(iconRes: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Green1
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
