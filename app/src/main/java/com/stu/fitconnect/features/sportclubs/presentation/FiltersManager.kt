package com.stu.fitconnect.features.sportclubs.presentation

import com.stu.fitconnect.features.sportclubs.domain.entity.Filter
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.utils.ResourceJsonManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FiltersManager @Inject constructor(
    private val resourceJsonManager: ResourceJsonManager
) {

    private val mutableFilterState = MutableStateFlow(resourceJsonManager.getFilters())
    val filtersState: StateFlow<SportClubsFiltersData> = mutableFilterState.asStateFlow()

    fun setFilters(data: SportClubsFiltersData) {
        mutableFilterState.update {
            it.copy(filtersCategoryList = data.filtersCategoryList)
        }
    }

    fun setSingleFilter(filter: Filter) {
        mutableFilterState.update {
            it.copy(filtersCategoryList = it.filtersCategoryList.map { category ->
                if (category.id == filter.categoryId) {
                    category.copy(filtersList = category.filtersList.map { filterData ->
                        if (filterData.id == filter.id)
                            filterData.copy(isSelect = !filterData.isSelect)
                        else
                            filterData
                    })
                } else
                    category
            })
        }
    }
}