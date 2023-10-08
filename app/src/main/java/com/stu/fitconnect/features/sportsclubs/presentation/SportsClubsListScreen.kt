package com.stu.fitconnect.features.sportsclubs.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewSportsClubsListScreen() {
    SportsClubsListScreen()
}

@ExperimentalMaterial3Api
@Composable
fun SportsClubsListScreen() {
    var searchText by remember { mutableStateOf("") }

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp)
            )

            // Полоска фильтров (добавьте вашу логику фильтрации здесь)

            Spacer(modifier = Modifier.height(16.dp))

            // Paging список карточек спортзалов
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
//                items( /* ваш список спортзалов */) { sportsClub ->
//                    SportsClubCard(sportsClub)
//                }
            }
        }
    }
}

@Composable
fun SportsClubCard(sportsClub: SportsClub) {
    // Ваш макет карточки спортзала
    // Используйте sportsClub для заполнения данных карточки
}

data class SportsClub(val name: String, val address: String, val imageResId: Int)