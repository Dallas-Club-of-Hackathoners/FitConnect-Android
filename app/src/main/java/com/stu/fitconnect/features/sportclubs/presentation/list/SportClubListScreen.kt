@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package com.stu.fitconnect.features.sportclubs.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.stu.fitconnect.R
import com.stu.fitconnect.base.use
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.composables.AppIconButton
import com.stu.fitconnect.composables.AppOutlineButton
import com.stu.fitconnect.composables.SearchTextField
import com.stu.fitconnect.features.sportclubs.domain.entity.Filter
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Green
import kotlinx.coroutines.flow.emptyFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SportsClubsListRoute(
    viewModel: SportClubListViewModel = hiltViewModel(),
    onNavigateToDetailSportsClubsScreen: (sportClubId: Int) -> Unit,
    onNavigateToFiltersSportsClubsScreen: () -> Unit,
) {
    val (state, event) = use(viewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        event.invoke(SportClubListContract.Event.OnGetSportClubFilters)
        if(viewModel.state.value.pagingSportClubList == emptyFlow<PagingData<SportClubSummary>>()) {
            event.invoke(SportClubListContract.Event.OnGetSportClub)
        }
    }
    Scaffold {
        SportsClubsListScreen(
            sportsClubsListState = state,
            sportClubsListLazyListState = rememberLazyListState(),
            onNavigateToDetailSportsClubsScreen = onNavigateToDetailSportsClubsScreen,
            onNavigateToFiltersSportsClubsScreen = onNavigateToFiltersSportsClubsScreen,
            onSearch = { searchBy ->
                event.invoke(SportClubListContract.Event.OnSearchSportClub(searchBy = searchBy))
            },
            onSelectFilter = { filter ->
                event.invoke(SportClubListContract.Event.OnSelectFilter(filter = filter))
            },
            onRefresh = {
                event.invoke(SportClubListContract.Event.OnRefresh)
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun SportsClubsListScreen(
    sportsClubsListState: SportClubListContract.State,
    sportClubsListLazyListState: LazyListState,
    onNavigateToDetailSportsClubsScreen: (sportClubId: Int) -> Unit,
    onNavigateToFiltersSportsClubsScreen: () -> Unit,
    onSearch: (searchBy: String) -> Unit,
    onSelectFilter: (filters: Filter) -> Unit,
    onRefresh: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        SearchSection(
            sportsClubsListState = sportsClubsListState,
            onSearch = onSearch,
            onNavigateToFiltersSportsClubsScreen = onNavigateToFiltersSportsClubsScreen,
            onFilterClick = {
                onSelectFilter(it)
            }
        )
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
                state = sportClubsListLazyListState,
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(35.dp)
            ) {
                items(
                    sportsClubsPagingItems.itemCount,
                    key = {
                        sportsClubsPagingItems[it]?.id ?: null as Any
                    },
                ) { index ->
                    sportsClubsPagingItems[index]?.let {
                        SportClubCard(
                            sportClub = it,
                            onCardClick = { sportClubId ->
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


@Composable
fun SearchSection(
    sportsClubsListState: SportClubListContract.State,
    onSearch: (String) -> Unit,
    onNavigateToFiltersSportsClubsScreen: () -> Unit,
    onFilterClick: (Filter) -> Unit
) {
    SearchTextField(
        value = sportsClubsListState.searchText,
        onValueChange = { newText ->
            onSearch(newText)
        },
        label = stringResource(R.string.find_sport_club_label)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        AppIconButton(
            onClick = onNavigateToFiltersSportsClubsScreen,
            icon = Icons.Default.Menu
        )

        val favouriteFilter = sportsClubsListState.selectedFilters?.filtersCategoryList?.last()?.filtersList?.get(1)
        AppIconButton(
            onClick = {
                if (favouriteFilter != null)
                    onFilterClick(favouriteFilter)
            },
            icon = Icons.Default.FavoriteBorder
        )

        AppOutlineButton(
            onClick = { /*TODO*/ },
            textValue = "Рекомендуем",
            textStyle = MaterialTheme.typography.headlineSmall
        )
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
            sportClubsListLazyListState = rememberLazyListState(),
            onNavigateToDetailSportsClubsScreen = {},
            onNavigateToFiltersSportsClubsScreen = {},
            onSearch = {},
            onSelectFilter = {},
            onRefresh = {}
        )
    }
}
