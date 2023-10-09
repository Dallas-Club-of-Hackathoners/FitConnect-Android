package com.stu.fitconnect.features.sportactivities.domain



//import com.stu.fitconnect.features.sportsclubs.domain.ClubsCategory
//import com.stu.fitconnect.features.sportsclubs.domain.Trainer
//
//data class SportClubActivityFilters(
//    val isFavorite: Boolean = false,
//    val clubsCategory: List<ClubsCategory> = emptyList(),
//    val trainers: List<Trainer> = emptyList(),
//    val time: List<String> = emptyList()
//)
// todo ПРИНИЦП DRY? - СОСАЙ крч если найдем как надо это переписать SportClubFilters.kt
data class SportClubActivityFilters(
    var filterList: List<FilterCategoryActivities> = emptyList()
) {
    fun getUpdatedFiltersList(filter: FilterActivities) : SportClubActivityFilters {
        val filtersData = this
        filtersData.filterList.forEach { filterCategory ->
            if (filterCategory.id == filter.id) {
                filterCategory.filterList.forEach { filterItem ->
                    if (filterItem.id == filter.id) {
                        filterItem.isSelected = !filterItem.isSelected
                    }
                }
            }
        }
        return filtersData
    }
}

data class FilterCategoryActivities (
    val id: Int,
    val name: String,
    val filterList: List<FilterActivities>
)

data class FilterActivities (
    val id:Int,
    val name: String,
    val categoryId:Int,
    val imageRes:Int? = null,
    val time: String? = null,
    var isSelected: Boolean = false
)

data class SortType(
    val id: Int = 0,
    val name: String,
)

/*
использовать этот филтр для всех????

data class Filter(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val imageRes: Int? = null,
    val count: Int? = null,
    var isSelect: Boolean = false
)

 */

