package com.stu.fitconnect.features.sportsclubs.domain

data class SportsClubsFilters(
    private val isFavouriteFilter: IsFavouriteFilter = IsFavouriteFilter(),
    private val facilities: MutableList<Facility> = mutableListOf(),
    private val costs: MutableList<Cost> = mutableListOf(),
    private val clubsCategories: MutableList<ClubsCategory> = mutableListOf(),
    private var sortType: SortType? = null
) {

    fun selectIsFavouriteFilter() : SportsClubsFilters{
        this.isFavouriteFilter.isFavourite = !this.isFavouriteFilter.isFavourite
        return this
    }
    fun selectSortType(sortType: SortType?) : SportsClubsFilters {
        this.sortType = sortType
        return this
    }

    fun selectFacility(facility: Facility) : SportsClubsFilters {
        val index = this.facilities.indexOfFirst { it.id == facility.id }
        if (index != -1)
            this.facilities.removeAt(index)
        else
            this.facilities.add(facility)
        return this

    }

    fun selectCost(cost: Cost) : SportsClubsFilters{
        val index = this.costs.indexOfFirst { it.id == cost.id }
        if (index != -1)
            this.costs.removeAt(index)
        else
            this.costs.add(cost)
        return this

    }
    fun selectClubsCategory(clubsCategory: ClubsCategory) : SportsClubsFilters{
        val index = this.clubsCategories.indexOfFirst { it.id == clubsCategory.id }
        if (index != -1)
            this.clubsCategories.removeAt(index)
        else
            this.clubsCategories.add(clubsCategory)
        return this
    }

//    fun <T : Filter> selectFilterItem(item: T) : SportsClubsFilters {
//        val filterList = when (item) {
//            is Facility -> this.facilities
//            is Cost -> this.costs
//            is ClubsCategory -> this.clubsCategories
//            else -> return this // Обработка других типов, если необходимо
//        }
//
//        val index = filterList.indexOfFirst { it.id == item.id }
//        if (index != -1)
//            filterList.removeAt(index)
//        else
//            filterList.add(item as Filter)
//        return this
//
//    }

}


interface Filter

data class IsFavouriteFilter(
    var isFavourite: Boolean = false
): Filter

data class Facility(
    var id: String,
    var name: String,
    var iconRes: Int,
): Filter

data class Cost(
    var id: String,
    var count: Int,
): Filter

data class ClubsCategory(
    var id: String,
    var name: String,
): Filter

data class SortType(
    var id: String,
    var name: String,
): Filter

//// список фильтров
//
//data class ShortClubFilters(
//
//)
//
enum class FilterType {
    IS_FAVOURITE,
    FACILITIES,
    COST,
    CLUB_CATEGORY,
    SORT_TYPE
}

data class FilterCategoryData(
    val id: String,
    val filterType: String,
    val filtersList: List<FilterData>
)

data class FilterData(
    val id: String,
    val filterType: String,
    val name: String,
    val imageRes: Int? = null
)
//
//data class IsFavouriteFilter(
//    val id: String,
//    val count: Int,
//    val isSelected: Boolean = false
//): Filter
//
//data class Facility(
//    val id: String,
//    val name: String,
//    val iconRes: Int,
//    val isSelected: Boolean = false
//) : Filter
//

//
//data class ClubsCategory(
//    val id: String,
//    val name: String,
//    val isSelected: Boolean = false
//): Filter
//
//data class SortType(
//    val id: String,
//    val name: String,
//    val isSelected: Boolean = false
//): Filter
//
//

