package com.stu.fitconnect.features.sportclubs.presentation.filters

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stu.fitconnect.base.use
import com.stu.fitconnect.composables.FiltersCard
import com.stu.fitconnect.features.sportclubs.domain.entity.Filter
import com.stu.fitconnect.features.sportclubs.presentation.FiltersManager
import com.stu.fitconnect.ui.theme.ButtonColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SportClubsFiltersRoute(
    viewModel: SportClubsFiltersViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        if(viewModel.state.value.filtersData.filtersCategoryList.isEmpty())
            event.invoke(SportClubsFilterContract.Event.OnGetAllFilters)
    }

    Surface {
        SportClubsFiltersScreen(
            state = state,
            sportClubsFiltersLazyListState = rememberLazyListState(),
            onFilterSelected = { filters ->
                event.invoke(SportClubsFilterContract.Event.OnSelectFilter(filter = filters))
            },
            onApplyFilters = {
                event.invoke(SportClubsFilterContract.Event.OnApplyFilters(onNavigateBack = onNavigateBack))
            },
        )
    }

}

@Composable
fun SportClubsFiltersScreen(
    state: SportClubsFilterContract.State,
    sportClubsFiltersLazyListState: LazyListState,
    onFilterSelected: (Filter) -> Unit,
    onApplyFilters: () -> Unit
) {
    val filterData = state.filtersData

    Column {
        LazyColumn(
            state = sportClubsFiltersLazyListState,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            items(
                state.filtersData.filtersCategoryList.size,
                key = {
                    state.filtersData.filtersCategoryList[it].id
                },
            ) { index ->
                FiltersCard(
                    filterCategory = state.filtersData.filtersCategoryList[index],
                    onFilterSelected = onFilterSelected
                )
            }
        }
        Button(
            onClick = onApplyFilters,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {

        }
    }

}


@Composable
fun FilterCategory() {

}


@Preview
@Composable
fun PreviewSportClubsFiltersScreen() {
    //SportClubsFiltersScreen()

}