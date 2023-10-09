package com.stu.fitconnect.features.sportsclubs.presentation.list

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportsclubs.domain.Filter
import com.stu.fitconnect.features.sportsclubs.domain.FilterCategoryData
import com.stu.fitconnect.features.sportsclubs.domain.SortType
import com.stu.fitconnect.features.sportsclubs.domain.SportsClubsFiltersData
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
        SportsClubListContract.Event.OnRefresh -> onRefresh()
        SportsClubListContract.Event.OnGetSportsClubFilters -> getAllSportsClubFiltersData()
        SportsClubListContract.Event.OnGetSportsClub -> getSportsClubsPagingList()
        is SportsClubListContract.Event.OnSearchSportsClub -> updateStateAndFetchData { it.copy(searchText = event.searchBy) }
        is SportsClubListContract.Event.OnApplySingleFilter -> updateStateAndFetchData { it.copy(selectedFilters = it.selectedFilters.getUpdatedFiltersList(event.filter)) }
        is SportsClubListContract.Event.OnApplySelectedFilters -> updateStateAndFetchData { it.copy(selectedFilters = event.sportsClubsFilters) }
    }

    fun getAllSportsClubFiltersData() {
        val inputStream = Resources.getSystem().openRawResource(R.raw.sports_clubs_filters_data)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        mutableScreenState.update{
            it.copy(selectedFilters = Gson().fromJson(jsonString, SportsClubsFiltersData::class.java))
        }
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

    private fun updateStateAndFetchData(updateAction: (SportsClubListContract.State) -> SportsClubListContract.State) {
        mutableScreenState.update { updateAction(it) }
        getSportsClubsPagingList()
    }

    private fun onRefresh() {
        mutableScreenState.update { it.copy(refreshing = true) }
        getSportsClubsPagingList()
        mutableScreenState.update { it.copy(refreshing = false) }
    }
}