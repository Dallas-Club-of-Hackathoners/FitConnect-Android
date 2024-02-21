package com.stu.fitconnect.features.sportclubs.domain.entity


data class SportClubsFiltersData(
    var filtersCategoryList: List<FilterCategory>,
//    val filtersList:
//    var sortType: SortType = SortType()
) {

    fun getUpdatedFiltersList(filter: Filter) : SportClubsFiltersData {

        val updatedFiltersCategoryList = filtersCategoryList.map { filterCategory ->
            if (filterCategory.id == filter.categoryId) {
                val updatedFiltersList = filterCategory.filtersList.map { filterItem ->
                    if (filterItem.id == filter.id) {
                        filterItem.copy(isSelect = !filterItem.isSelect)
                    } else {
                        filterItem
                    }
                }
                filterCategory.copy(filtersList = updatedFiltersList)
            } else {
                filterCategory
            }
        }
        return SportClubsFiltersData(updatedFiltersCategoryList)
    }

    fun updateSortType(sortType: SortType) : SportClubsFiltersData {
//        this.sortType = sortType
        return this
    }

//    fun getFiltersWithSelectFilter(filter: Filter): SportClubsFiltersData {
//        val filterData = this.copy(filtersCategoryList = this.filtersCategoryList.find { it.id == filter.categoryId }?.filtersList?.find { it.id == filter.id })
//        return this.copy()
//
//    }
}


data class FilterCategory(
    val id: Int,
    val name: String,
    val filtersList: List<Filter>
)

data class Filter(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val imageRes: Int? = null,
    val count: Int? = null,
    val isSelect: Boolean = false
)

data class SortType(
    val id: Int,
    val name: String,
    val isSelect: Boolean,
)

enum class FilterType {
    IS_FAVOURITE,
    FACILITIES,
    COST,
    CLUB_CATEGORY,
    SORT_TYPE
}


//data class SportsClubsFilters(
//    private val isFavouriteFilter: IsFavouriteFilter = IsFavouriteFilter(),
//    private val facilities: MutableList<Facility> = mutableListOf(),
//    private val costs: MutableList<Cost> = mutableListOf(),
//    private val clubsCategories: MutableList<ClubsCategory> = mutableListOf(),
//    private var sortType: SortType? = null
//) {
//
//    fun selectIsFavouriteFilter() : SportsClubsFilters{
//        this.isFavouriteFilter.isFavourite = !this.isFavouriteFilter.isFavourite
//        return this
//    }
//    fun selectSortType(sortType: SortType?) : SportsClubsFilters {
//        this.sortType = sortType
//        return this
//    }
//
//    fun selectFacility(facility: Facility) : SportsClubsFilters {
//        val index = this.facilities.indexOfFirst { it.id == facility.id }
//        if (index != -1)
//            this.facilities.removeAt(index)
//        else
//            this.facilities.add(facility)
//        return this
//
//    }
//
//    fun selectCost(cost: Cost) : SportsClubsFilters{
//        val index = this.costs.indexOfFirst { it.id == cost.id }
//        if (index != -1)
//            this.costs.removeAt(index)
//        else
//            this.costs.add(cost)
//        return this
//
//    }
//    fun selectClubsCategory(clubsCategory: ClubsCategory) : SportsClubsFilters{
//        val index = this.clubsCategories.indexOfFirst { it.id == clubsCategory.id }
//        if (index != -1)
//            this.clubsCategories.removeAt(index)
//        else
//            this.clubsCategories.add(clubsCategory)
//        return this
//    }
//}
//
//

data class FiltersData(
    private val isFavouriteFilter: IsFavouriteFilter,
    private val facilities: Map<Int, AmenityFilter>,
    private val costs: Map<Int, CostFilter>,
    private val clubsCategories: Map<Int, ClubsCategoryFilter>,
    private var sortTypes: Map<Int, SortType>
) {
    fun getFiltersWithSelectClubsCategory(clubsCategoryId: Int) : FiltersData? {
        val category = this.clubsCategories[clubsCategoryId]
        return if(category != null)
            copy(clubsCategories = clubsCategories.toMutableMap().apply {
                this[clubsCategoryId] = category.copy(isSelect = category.isSelect)
            })
        else null
    }
}

interface FilterT

data class IsFavouriteFilter(
    val isSelect: Boolean
): FilterT {
    fun getSelectIsFavourite(): IsFavouriteFilter {
        return copy(isSelect = !isSelect)
    }
}

data class AmenityFilter(
    val id: String,
    val name: String,
    val iconRes: Int,
    val isSelect: Boolean
): FilterT {
    fun getSelectFacility(): AmenityFilter {
        return copy(isSelect = !isSelect)
    }
}

data class CostFilter(
    val id: String,
    val count: Int,
    val isSelect: Boolean
): FilterT {
    fun getSelectCost(): CostFilter {
        return copy(isSelect = !isSelect)
    }
}

data class ClubsCategoryFilter(
    val id: String,
    val name: String,
    val isSelect: Boolean
): FilterT {
    fun getSelectClubsCategory(): ClubsCategoryFilter {
        return copy(isSelect = !isSelect)
    }
}

//// список фильтров
//
//data class ShortClubFilters(
//
//)
//
