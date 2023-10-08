package com.stu.fitconnect.features.sportsclubs.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu.fitconnect.features.sportsclubs.domain.ClubsCategory
import com.stu.fitconnect.features.sportsclubs.domain.Cost
import com.stu.fitconnect.features.sportsclubs.domain.Facility
import com.stu.fitconnect.features.sportsclubs.domain.Filter
import com.stu.fitconnect.features.sportsclubs.domain.IsFavouriteFilter
import com.stu.fitconnect.features.sportsclubs.domain.SortType
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFilters
import com.stu.fitconnect.features.sportsclubs.domain.usecases.GetSportsClubsListUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import javax.inject.Inject

@ViewModelScoped
class SportsClubListViewModel @Inject constructor(
    private val getSportsClubsListUseCase: GetSportsClubsListUseCase
): ViewModel(), SportsClubListContract {

    private val mutableScreenState = MutableStateFlow(SportsClubListContract.State())
    override val state: StateFlow<SportsClubListContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SportsClubListContract.Event) = when (event) {
        is SportsClubListContract.Event.OnSearchSportsClub -> onSearchBy(event.searchBy)
        is SportsClubListContract.Event.OnSortSelected -> onSortSelected(event.sortType)
        is SportsClubListContract.Event.OnApplySelectedFilters -> applySelectedFilters(event.sportsClubsFilters)
        SportsClubListContract.Event.OnRefresh -> onRefresh()
    }

    init {
        getSportsClubsPagingList()
    }

    private fun getSportsClubsPagingList() {
        mutableScreenState.update { it.copy(isLoading = true) }
        try {
            viewModelScope.launch {
                val pagingList = getSportsClubsListUseCase.getSportsClubsPagingList(
                    mutableScreenState.value.searchText,
                    mutableScreenState.value.selectedFilters)
                mutableScreenState.update { it.copy(pagingSportsClubList = pagingList) }
            }
        } catch (e: Exception) {
            //handle exception
            // add side effect?
        } finally {
            mutableScreenState.update { it.copy(isLoading = false) }
        }
    }

    private fun onSortSelected(sortType: SortType) {
        mutableScreenState.update { it.copy(selectedFilters = getSportsClubFiltersBySelectFilter(sortType, it.selectedFilters)) }
        getSportsClubsPagingList()
    }

    private fun onRefresh() {
        mutableScreenState.update { it.copy(refreshing = true) }
        getSportsClubsPagingList()
        mutableScreenState.update { it.copy(refreshing = false) }
    }

    private fun onSearchBy(searchBy: String) {
        mutableScreenState.update { it.copy(searchText = searchBy) }
        getSportsClubsPagingList()
    }

    private fun applySelectedFilters(sportsClubsFilters: SportsClubsFilters) {
        mutableScreenState.update { it.copy(selectedFilters = sportsClubsFilters) }
        getSportsClubsPagingList()
    }

    private fun getSportsClubFiltersBySelectFilter(filter: Filter, sportsClubsFilters: SportsClubsFilters) : SportsClubsFilters {
        when(filter) {
            is IsFavouriteFilter -> sportsClubsFilters?.selectIsFavouriteFilter()
            is Facility -> sportsClubsFilters?.selectFacility(filter)
            is Cost -> sportsClubsFilters?.selectCost(filter)
            is ClubsCategory -> sportsClubsFilters?.selectClubsCategory(filter)
            is SortType -> sportsClubsFilters?.selectSortType(filter)
        }
        throw IllegalStateException("Unexpected filter type")
    }
}