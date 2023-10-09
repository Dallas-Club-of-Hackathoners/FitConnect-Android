package com.stu.fitconnect.features.sportclubs.domain


data class SportClubsFiltersData(
    var filtersList: List<FilterCategoryData> = emptyList(),
//    var sortType: SortType = SortType()
) {

    fun getUpdatedFiltersList(filter: Filter) : SportClubsFiltersData {
        val filtersData = this
        filtersData.filtersList.forEach { filterCategory ->
            if (filterCategory.id == filter.id) {
                filterCategory.filtersList.forEach { filterItem ->
                    if (filterItem.id == filter.id) {
                        filterItem.isSelect = !filterItem.isSelect
                    }
                }
            }
        }
        return filtersData
    }

    fun updateSortType(sortType: SortType) : SportClubsFiltersData  {
//        this.sortType = sortType
        return this
    }
}


data class FilterCategoryData(
    val id: Int,
    val name: String,
    val filtersList: MutableList<Filter> = mutableListOf()
)

data class Filter(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val imageRes: Int? = null,
    val count: Int? = null,
    var isSelect: Boolean = false
)

data class SortType(
    var id: Int = 0,
    var name: String = "",
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
//interface Filter
//
//data class IsFavouriteFilter(
//    var isFavourite: Boolean = false
//): Filter
//
data class Facility(
    var id: String,
    var name: String,
    var iconRes: Int,
)
//
//data class Cost(
//    var id: String,
//    var count: Int,
//): Filter
//
//data class ClubsCategory(
//    var id: String,
//    var name: String,
//): Filter
//
//// список фильтров
//
//data class ShortClubFilters(
//
//)
//
